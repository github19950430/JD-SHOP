package com.example.jdconsumerorder.service;

import com.example.jdproducerorder.entity.solr.OrderVo;

public interface OrderService {

    String createOrder(OrderVo orderVo);
}
