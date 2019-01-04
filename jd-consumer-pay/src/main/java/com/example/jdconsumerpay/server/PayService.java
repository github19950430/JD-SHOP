package com.example.jdconsumerpay.server;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface PayService {
    //验证支付数据
    String payorder(String out_trade_no, String trade_no, String total_amount,HttpServletRequest request) throws IOException;

}
