package com.example.jdproducerorder.service.impl;

import com.example.jdproducerorder.dao.OrderDao;
import com.example.jdproducerorder.entity.order.Order;
import com.example.jdproducerorder.entity.order.OrderLogistics;
import com.example.jdproducerorder.dao.OrderLogisticsDao;
import com.example.jdproducerorder.service.OrderLogisticsService;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * (OrderLogistics)表服务实现类
 *
 * @author 阿飞！
 * @since 2018-12-26 10:13:58
 */
@Service("orderLogisticsService")
public class OrderLogisticsServiceImpl implements OrderLogisticsService {
    @Resource
    private OrderLogisticsDao orderLogisticsDao;

    @Resource
    private OrderDao orderDao;

    /**
     * 确认发货
     * @param orderLogistics
     * @return
     */
    @Override
    public OrderLogistics sendOut(OrderLogistics orderLogistics) throws Exception{
        Order order = new Order();
        order.setOrderNo(orderLogistics.getOrderNo());
        List<Order> orders = orderDao.queryAll(order);
        if(orders.get(0).getOrderStatus() != 1)
            throw new Exception("重复操作");
        //修改订单状态为已发货
        if(orders == null && (order = orders.get(0)) == null)
            throw new Exception("订单号有误!");
        order.setOrderStatus(2);
        orderDao.update(order);
        //设置发货时间 持久化到数据库
        orderLogistics.setSendTime(new Date());
        orderLogisticsDao.insert(orderLogistics);
        return orderLogistics;
    }

    /**
     * 确认收货
     *
     * @return
     */
    @Override
    @Transactional
    public int confirmReceipt(String logisticsNo) throws Exception {
        OrderLogistics orderLogistics = orderLogisticsDao.queryBylogisticsNo(logisticsNo);
        if(orderLogistics == null)
            throw new Exception("查询不到该运单号!");
        Order order = orderDao.queryByNo(orderLogistics.getOrderNo());
        Date date = new Date();
        //设置确认收货时间
        orderLogistics.setTakeTime(date);
        order.setOrderStatus(4);
        //持久化
        if (orderDao.update(order) > 0 && orderLogisticsDao.update(orderLogistics) > 0) {
            return 1;
        }
        return -1;
    }


    /**
     * 修改数据
     *
     * @param orderLogistics 实例对象
     * @return 实例对象
     */
    @Override
    public OrderLogistics update(OrderLogistics orderLogistics) {
        orderLogisticsDao.update(orderLogistics);
        return orderLogisticsDao.queryBylogisticsNo(orderLogistics.getLogisticsNo());
    }
}