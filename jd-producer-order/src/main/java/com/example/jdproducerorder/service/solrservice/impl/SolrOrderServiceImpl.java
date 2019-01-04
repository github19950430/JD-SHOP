package com.example.jdproducerorder.service.solrservice.impl;

import com.example.jdproducerorder.dao.OrderDao;
import com.example.jdproducerorder.dao.OrderDetailDao;
import com.example.jdproducerorder.entity.order.Order;
import com.example.jdproducerorder.entity.order.OrderDetail;
import com.example.jdproducerorder.entity.solr.OrderDetailVo;
import com.example.jdproducerorder.entity.solr.OrderVo;
import com.example.jdproducerorder.service.OrderService;
import com.example.jdproducerorder.service.impl.OrderServiceImpl;
import com.example.jdproducerorder.service.solrservice.SolrOrderService;
import lombok.extern.java.Log;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

@Service
@Log
public class SolrOrderServiceImpl implements SolrOrderService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private OrderDetailDao orderDetailDao;

    @Resource
    private OrderServiceImpl orderService;

    private static final String solrUrl="http://188.131.133.118:8983/solr/order-core";//订单core
    //创建solrClient同时指定超时时间，不指定走默认配置
    private static HttpSolrClient client=new HttpSolrClient.Builder(solrUrl).build();

    /**
     * 查询多条数据 导到solr
     *
     * @return 对象列表
     */
    @Override
    @Scheduled(fixedRate = 5000) //5秒一次
    public void queryAllDB2Solr() throws IOException, SolrServerException {

        List<Order> orders =  orderDao.queryAll(new Order());
//        log.info("orders :" + orders);
        List<OrderDetail> orderDetails = orderDetailDao.queryAll(new OrderDetail());
//        log.info("orders is " + orders);
//        log.info("orderDetails is " + orderDetails);

        insertDate2Solr(orders, orderDetails);

    }

    private void insertDate2Solr(List<Order> orders, List<OrderDetail> orderDetails) throws SolrServerException, IOException {

        for (Order order : orders) {
            SolrInputDocument document = new SolrInputDocument();
            //id
            document.addField("id", order.getOrderNo() + ",order," + order.getOrderId());
            //状态
            document.addField("order_status", order.getOrderStatus());
            //订单编号
            document.addField("order_no", order.getOrderNo());
            //用户id
            document.addField("user_id", order.getUserId());
            //商店id
            document.addField("store_id", order.getStoreId());
            //商店名称
            document.addField("store_name", order.getStoreName());
            //商品总金额
            document.addField("product_amount_total", order.getProductAmountTotal());
            //订单总金额
            document.addField("order_amount_total", order.getOrderAmountTotal());
            //优惠总金额
            document.addField("coupon_money_total", order.getCouponMoney());
            //运费
            document.addField("logistics_fee", order.getLogisticsFee());
            //地址
            document.addField("address", order.getAddress());
            //物流单号
            document.addField("orderlogistics_no", order.getOrderlogisticsNo());
            //支付方式
            document.addField("pay_channel", order.getPayChannel());
            //订单创建时间
            document.addField("create_time", order.getCreateTime());
            //支付时间
//            log.info("pay_time is " + order.getPayTime());
            document.addField("pay_time", order.getPayTime());
            //订单结算状态--》分期付款，货到付款之类
            document.addField("order_settlement_status", order.getOrderSettlementStatus());

            client.add(document);
        }

        for(OrderDetail orderDetail :orderDetails) {
            SolrInputDocument document = new SolrInputDocument();
            document.addField("id",orderDetail.getOrderNo() + ",orderDetail," + orderDetail.getOrderDetailId());
            document.addField("order_no", orderDetail.getOrderNo());
            document.addField("product_id", orderDetail.getProductId());
//            log.info("num is " + orderDetail.getNumber());
            document.addField("number", orderDetail.getNumber());
            document.addField("product_name", orderDetail.getProductName());
            document.addField("product_price", orderDetail.getProductPrice());
            document.addField("product_sku", orderDetail.getProductSku());
            document.addField("status",orderDetail.getStatus());
            client.add(document);
        }

        client.commit();
    }

    /**
     * 删除solr中所有的数据
     *
     * @return
     */
    @Override
    public String deleteAll() throws IOException, SolrServerException {
        String queryAll = "*:*";
        return client.deleteByQuery(queryAll) + "";
    }

    @Override
    public String deleteByNo(String userid, String no) throws IOException, SolrServerException{
        String queryById = /*"user_id:" + userid + */" AND order_no:" + no;
        return client.deleteByQuery(queryById).getException().toString();
    }

    /**
     *可以修改 状态 运单号 支付时间 交易单号
     */
    @Override
    public String updateByNo(OrderVo orderVo) throws Exception {
        OrderVo vo = orderService.searchOrder(orderVo.getUserId(), orderVo.getOrderStatus(), orderVo.getOrderNo());
        if(orderVo.getOrderStatus() != null)
            vo.setOrderStatus(orderVo.getOrderStatus());
        if(orderVo.getOrderlogisticsNo() != null)
            vo.setOrderlogisticsNo(orderVo.getOrderlogisticsNo());
        if(orderVo.getPayTime() != null)
            vo.setPayTime(orderVo.getPayTime());
        if(orderVo.getOutTradeNo() != null)
            vo.setOutTradeNo(orderVo.getOutTradeNo());
        return "1";
    }

    @Override
    public String create(OrderVo orderVo) throws IOException, SolrServerException {
        Order order = orderService.assembleOrder(orderVo);
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        List<OrderDetail> orderDetails = orderService.assembleOrderDetail(orderVo);
        insertDate2Solr(orders, orderDetails);
        return "1";
    }

    @Override
    public String create(List<Order> orders, List<OrderDetail> orderDetails) throws IOException, SolrServerException {
        insertDate2Solr(orders, orderDetails);
        return "1";
    }

}
