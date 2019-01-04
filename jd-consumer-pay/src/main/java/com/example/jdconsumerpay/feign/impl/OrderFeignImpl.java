package com.example.jdconsumerpay.feign.impl;

import com.example.jdconsumerpay.feign.OrderFeign;
import org.springframework.stereotype.Component;

@Component
public class OrderFeignImpl implements OrderFeign {
    @Override
    public String noPayment2payment(String orderNo, Integer userId, String payNo, String payTime) {
        return "0000";
    }
}
