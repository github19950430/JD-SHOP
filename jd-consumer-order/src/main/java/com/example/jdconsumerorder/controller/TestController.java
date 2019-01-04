package com.example.jdconsumerorder.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.jdconsumerorder.service.OrderService;
import com.example.jdproducerorder.entity.solr.OrderVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class TestController {

    @Resource
    private OrderService orderService;

    @GetMapping(value = "create")
    public String create(@RequestParam("orderVo") String orderVo) {
        System.out.println(orderVo);
//        Object parse = JSON.parse(orderVo, OrderVo.class);
//        OrderVo orderVo1 = JSON.parseObject(orderVo, OrderVo.class);
//        System.out.println(orderVo);
        return orderService.createOrder(JSON.parseObject(orderVo,OrderVo.class));
    }
}
