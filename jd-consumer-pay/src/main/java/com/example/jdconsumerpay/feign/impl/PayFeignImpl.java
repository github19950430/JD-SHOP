package com.example.jdconsumerpay.feign.impl;

import com.example.jdconsumerpay.feign.PayFeign;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
@Component
public class PayFeignImpl implements PayFeign {
    @Override
    public String alipayReturn_url(String out_trade_no, String trade_no, String total_amount, HttpServletRequest request) throws IOException {
        return "0000";
    }

    @Override
    public String refund(String WIDTRout_trade_no, String WIDTRtrade_no, String WIDTRrefund_amount, String WIDTRrefund_reason, String WIDTRout_request_no) {
        return "0000";
    }

    @Override
    public String pay(String WIDtotal_amount, String WIDsubject, String WIDorderID, Integer WIDuserid) {
        return null;
    }
}
