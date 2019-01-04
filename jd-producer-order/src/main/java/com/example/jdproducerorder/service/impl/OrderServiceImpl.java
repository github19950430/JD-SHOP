package com.example.jdproducerorder.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.jdproducerorder.RabbitMQUitl.RabbitConfig;
import com.example.jdproducerorder.dao.OrderDao;
import com.example.jdproducerorder.dao.OrderDetailDao;
import com.example.jdproducerorder.entity.order.Order;
import com.example.jdproducerorder.entity.order.OrderDetail;
import com.example.jdproducerorder.entity.solr.OrderDetailVo;
import com.example.jdproducerorder.entity.solr.OrderSearch;
import com.example.jdproducerorder.entity.solr.OrderVo;
import com.example.jdproducerorder.service.OrderService;
import com.example.jdproducerorder.solr.SolrOrderDao;
import com.example.jdproducerorder.utils.BigDecimalUtil;
import com.example.jdproducerorder.utils.RedisUtil;
import lombok.extern.java.Log;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Log
public class OrderServiceImpl implements OrderService {

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private SolrOrderDao solrOrderDao;

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Resource
    private OrderDao orderDao;

    @Resource
    private OrderDetailDao orderDetailDao;

    /**
     * 创建订单
     *
     * @param orderVos
     * @return
     */
    @Override
    public List<OrderVo> createOrder(OrderVo ... orderVos) {
        List<OrderVo> list = new ArrayList<>();
        for (OrderVo orderVo : orderVos) {
            Order order = assembleOrder(orderVo);//生成订单
            List<OrderDetail> orderDetails = assembleOrderDetail(orderVo);//生成订单详情
            calOrderTotalPrice(orderVo, order);//计算订单价钱
            list.add(orderVo);
            //存入redis
//            log.info("order is : " + JSON.toJSONString(order));
            redisUtil.set(order.getOrderNo() + ",1," + orderVo.getUserId().toString(),
                    JSON.toJSONString(order), 1 * 60 * 30);

            redisUtil.set(order.getOrderNo() + ",2," + orderVo.getUserId().toString(),
                    JSON.toJSONString(orderDetails), 1 * 60 * 30);
            redisUtil.set(order.getOrderNo() + ",3," + orderVo.getUserId().toString(),
                    JSON.toJSONString(orderVo), 1 * 60 *30);
            //发往队列
            rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_CREATE, order.getOrderNo());
        }
        return list;
    }

    /**
     * 搜索订单
     *
     * @param userid
     * @param status
     * @return
     */
    @Override
    public List<OrderVo> searchOrder(Integer userid, Integer status,Integer index) throws Exception{
        if(index < 1)
            index =1;
        log.info("userid :" + userid + " status: " + status);
        List<OrderVo> orderVos = new ArrayList<>();
        List<OrderDetail> orderDetails = null;
        //未付款的从redis中查询
        if(status == 0){
            return getRedisOrderVos(userid, orderVos);
        }

        OrderSearch orderSearch = new OrderSearch();
        String sb = "user_id:" + userid + " AND order_status:" + status;

        orderSearch.setPageSize((index - 1) * 5);
        System.out.println(index * 5 - 1 + "-------------------");
//        orderSearch.setState("1");
//        orderSearch.setId(1);
        orderSearch.setQueryString(sb.toString());



        orderVos = solrOrderDao.searchOrder(orderSearch);

        for (OrderVo orderVo : orderVos) {
            String detail = "product_sku:* AND order_no:" + orderVo.getOrderNo();
            orderSearch.setQueryString(detail);
            orderVo.setOrderDetailVos(solrOrderDao.searchOrderDetail(orderSearch));
        }

        return orderVos;
    }

    /**
     * 搜索订单通过用户id
     *
     * @param userid
     * @return
     */
    @Override
    public List<OrderVo> searchOrderAll(Integer userid, Integer index) throws Exception {
        if(index < 1)
            index =1;
        List<OrderVo> orderVos = new ArrayList<>();
        orderVos = searchOrder(userid, 0, index);


        OrderSearch orderSearch = new OrderSearch();
        String sb = "address: * AND user_id:" + userid;

        orderSearch.setPageSize((index - 1) * 5);
        orderSearch.setQueryString(sb.toString());
        orderVos = solrOrderDao.searchOrder(orderSearch);

        for (OrderVo orderVo : orderVos) {
            String detail = "product_sku:* AND order_no:" + orderVo.getOrderNo();
            orderSearch.setQueryString(detail);
            orderVo.setOrderDetailVos(solrOrderDao.searchOrderDetail(orderSearch));
        }
        log.info("vos " + orderVos);
        return orderVos;
    }

    private List<OrderVo> getRedisOrderVos(Integer userid, List<OrderVo> orderVos) {
        Set<String> keys = redisTemplate.keys("*,3," + userid);
        log.info("keys: " + keys.toString());
        for (String key : keys){
            log.info("key is " + key);
            orderVos.add(redisUtil.get(key, OrderVo.class));
        }

        return orderVos;
    }

    /**
     * 搜索订单通过订单编号
     *
     * @param userid
     * @param orderNo
     * @return
     */
    @Override
    public OrderVo searchOrder(Integer userid, Integer status, String orderNo) throws Exception {
        List<OrderVo> orderVos = new ArrayList<>();
        //未付款的从redis中查询
        if(status == 0)
            return getRedisOrderVos(userid, orderVos).get(0);

        OrderSearch orderSearch = new OrderSearch();
        String sb = "user_id:" + userid + " AND order_no:" + orderNo;

        orderSearch.setPageSize(0);
        orderSearch.setQueryString(sb.toString());
        orderVos = solrOrderDao.searchOrder(orderSearch);
        OrderVo vo = solrOrderDao.searchOrder(orderSearch).get(0);
        //搜索订单详情
        String detail = "product_sku:* AND order_no:" + vo.getOrderNo();
        orderSearch.setQueryString(detail);
        vo.setOrderDetailVos(solrOrderDao.searchOrderDetail(orderSearch));
        return vo;
    }

    /**
     * 修改订单
     * 如果用此方法修改订单状态的话，只能正向更改（指流程 比如已付款-->已发货-->已签收）
     * 未付款状态的订单不在数据库存储
     * @param order
     * @return
     */
    @Override
    public int updateOrder(Order order) {
        if(order.getOrderId() != null && orderDao.queryById(order.getOrderId()).getOrderId() > order.getOrderId())
            return -1;
        return orderDao.update(order);
    }

    /**
     * 将未付款更改为已付款
     *
     * @param orderNo
     * @param userId
     * @return -1 持久化失败 -2 订单过期
     */
    @Override
    @Transactional
    public int noPayment2payment(String orderNo, Integer userId) {
        Order order = JSONObject.parseObject(redisUtil.get(orderNo + ",1," + userId) + "", Order.class);
        List<OrderDetail> orderDetails = JSONArray.parseArray(redisUtil.get(orderNo + ",2," + userId) + "", OrderDetail.class);
        if(order == null || orderDetails == null)
            return -2;
        //修改为已支付
        order.setOrderStatus(1);
        if (orderDao.insert(order) > 0 && orderDetailDao.insertAll(orderDetails) > 0){
            //删除redis中的数据
            redisUtil.del(orderNo + ",1," + userId,orderNo + ",2," + userId,orderNo + ",3," + userId);
            return 1;
        }
        return -1;
    }

    //生成订单详情
    public List<OrderDetail> assembleOrderDetail(OrderVo orderVo){
        List<OrderDetail> orderDetails = new ArrayList<>();
        for (OrderDetailVo vo : orderVo.getOrderDetailVos()){
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setNumber(vo.getNumber());
            orderDetail.setOrderNo(orderVo.getOrderNo());
            orderDetail.setProductSku(vo.getPeoductSku());
            orderDetail.setProductName(vo.getProductName());
            orderDetail.setProductPrice(vo.getProductPrice());
            orderDetails.add(orderDetail);
        }
        return orderDetails;
    }

    //生成订单
    public Order assembleOrder(OrderVo orderVo){
        Order order = new Order();
        //设置订单号
        order.setOrderNo(generateOrderNo());
        orderVo.setOrderNo(order.getOrderNo());//这里不要删除。同步数据
        //设置状态为未付款
        order.setOrderStatus(orderVo.getOrderStatus());
        //设置支付方式
        order.setPayChannel(orderVo.getPayChannel());
        //设置地址
        order.setAddress(orderVo.getAddress());
        //设置用户id
        order.setUserId(orderVo.getUserId());
        //商店名字
        order.setStoreName(orderVo.getStoreName());
        //创建订单创建时间
        order.setCreateTime(new Date());
        orderVo.setCreateTime(order.getCreateTime());
        return order;
    }

    //此函数用于生成订单号
    private String generateOrderNo(){
        //两位随机数
        String random = new Random().nextInt(100) + "";
        if(Integer.parseInt(random) < 10)
            random = 0 + random;
        //时间戳
        long currentTime = System.currentTimeMillis();
        //当前日期
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

        return simpleDateFormat.format(new Date(currentTime)) + currentTime + random;
    }



    /*
     * //计算订单总价
     */
    private void calOrderTotalPrice(OrderVo orderVo,Order order){
        BigDecimal payment = new BigDecimal("0");//订单总金额
        BigDecimal product = new BigDecimal("0");//商品总价
        BigDecimal logisticsFee = new BigDecimal("0");//运费总价
        BigDecimal coupon = new BigDecimal("0");//优惠总价

        /**
         * 用于计算运费
         * 同一商品运费只计算一次
         *
         * //这里计算运费的方式过于简陋，存在问题，日后完善
         */
        Integer flag = -1;

        //计算商品总价和运费总价
        for (OrderDetailVo detailVo : orderVo.getOrderDetailVos()) {
            log.info("detailVo is " + detailVo);
            //商品
            product = BigDecimalUtil.add(product.doubleValue(),
                     BigDecimalUtil.mul(detailVo.getProductPrice().doubleValue(),
                             detailVo.getNumber().doubleValue()).doubleValue());
            if (detailVo.getProductId() != flag) {
                //运费
                logisticsFee = BigDecimalUtil.add(payment.doubleValue(),detailVo.getLogisticsFee().doubleValue());
                flag = detailVo.getStoreId();
                //商家优惠券
                if(detailVo.getCouponTo() == 0) {
                    coupon = BigDecimalUtil.add(coupon.doubleValue(),
                            BigDecimalUtil.add(coupon.doubleValue(),
                            detailVo.getCouponMoney().doubleValue()).doubleValue());
                }
            }
            //商品优惠券
            if(detailVo.getCouponTo() == 1) {
                coupon = BigDecimalUtil.add(coupon.doubleValue(),
                        BigDecimalUtil.add(coupon.doubleValue(),
                                detailVo.getCouponMoney().doubleValue()).doubleValue());
            }
        }
        log.info("product: " + product +
                ",logisticsFee: " + logisticsFee +
                ",coupon: " + coupon);
        //计算订单总价
        payment = BigDecimalUtil.add(payment.doubleValue(),
                BigDecimalUtil.sub(BigDecimalUtil.add(product.doubleValue(),
                        logisticsFee.doubleValue()).doubleValue(),
                        coupon.doubleValue()).doubleValue());
        log.info("payment: " + payment);

        orderVo.setOrderAmountTotal(payment.doubleValue());
        orderVo.setLogisticsFeeTotal(logisticsFee.doubleValue());
        orderVo.setCouponMoneyTotal(coupon.doubleValue());
        orderVo.setProductAmountTotal(product.doubleValue());

        log.info("orderVo: " +orderVo);

        order.setOrderAmountTotal(payment.doubleValue());
        order.setLogisticsFee(logisticsFee.doubleValue());
        order.setCouponMoney(coupon.doubleValue());
        order.setProductAmountTotal(product.doubleValue());
    }


}
