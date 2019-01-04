package com.example.jdconsumerorder.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.jdconsumerorder.feign.OrderFeign;
import com.example.jdconsumerorder.feign.PayFeign;
import com.example.jdconsumerorder.service.OrderService;
import com.example.jdproducerorder.entity.solr.OrderVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderFeign orderFeign;

    @Resource
    private PayFeign payFeign;

    @Override
    public String createOrder(OrderVo orderVo) {
        String s = orderFeign.create(orderVo);
        JSONArray jsonArray = JSON.parseArray(s);
        JSONObject jsonObj = jsonArray.getJSONObject(0);
        String WIDtotal_amount = jsonObj.getString("orderAmountTotal");
        String WIDsubject = jsonObj.getString("storeName");
        String WIDorderID = jsonObj.getString("orderNo");
        Integer WIDuserid = jsonObj.getInteger("userId");

        System.out.println(WIDorderID  + " " + WIDsubject + " " + WIDtotal_amount + " " + WIDuserid);

        String pay = payFeign.pay(WIDtotal_amount, WIDsubject, WIDorderID, WIDuserid);

        System.out.println(pay);
        return pay;
    }
}
