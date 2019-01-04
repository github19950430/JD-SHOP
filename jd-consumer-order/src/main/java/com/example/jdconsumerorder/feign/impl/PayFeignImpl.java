package com.example.jdconsumerorder.feign.impl;

import com.example.jdconsumerorder.feign.PayFeign;
import org.springframework.stereotype.Component;

@Component
public class PayFeignImpl implements PayFeign {
    @Override
    public String pay(String WIDtotal_amount, String WIDsubject, String WIDorderID, Integer WIDuserid) {
        return null;
    }
}
