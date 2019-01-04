package com.example.jdproducerorder.service;


import com.alibaba.fastjson.JSONArray;
import com.example.jdproducerorder.entity.order.Order;
import com.example.jdproducerorder.entity.solr.OrderVo;

import java.util.List;

public interface OrderService {

    /**
     * 创建订单
     * @param orderVo
     * @return
     */
    List<OrderVo> createOrder(OrderVo ... orderVo);

    /**
     * 搜索订单通过订单状态
     * @param userid 可为空
     * @param status
     * @return
     */
    JSONArray searchOrder(Integer userid, Integer status, Integer index)  throws Exception;

    /**
     * 搜索订单通过用户id
     * @param userid
     * @return
     */
    JSONArray searchOrderAll(Integer userid, Integer index)  throws Exception;

    /**
     * 搜索订单通过订单编号
     * @param userid
     * @param status
     * @param orderNo
     * @return
     */
    OrderVo searchOrder(Integer userid, Integer status, String orderNo) throws Exception;

    /**
     * 修改订单
     * @param order
     * @return
     */
    int updateOrder(Order order);

    /**
     * 将未付款更改为已付款
     * @param orderNo
     * @param userId
     * @return
     */
    int noPayment2payment(String orderNo, Integer userId, String payNo, String payTime);
}
