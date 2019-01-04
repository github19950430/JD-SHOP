package com.example.jdconsumerpay.server.impl;

import com.example.jdconsumerpay.feign.OrderFeign;
import com.example.jdconsumerpay.feign.PayFeign;
import com.example.jdconsumerpay.redisutil.RedisUtil;
import com.example.jdconsumerpay.server.PayService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class PayServiceImpl implements PayService {

    @Resource
    private OrderFeign orderFeign;
    @Resource
    private PayFeign payFeign;
    @Resource
    private RedisUtil redisUtil;
    @Override
    public String payorder(String out_trade_no, String trade_no, String total_amount, HttpServletRequest request) throws IOException {

        String s = payFeign.alipayReturn_url(out_trade_no, trade_no, total_amount, request);
       if (!s.equals("验签失败")) {

                String userId = (String) redisUtil.get(out_trade_no + "userId");
           System.out.println("userid" + userId);
                long time = System.currentTimeMillis();  //时间戳 毫秒

                String order = orderFeign.noPayment2payment(out_trade_no, Integer.parseInt(userId),trade_no , time + "");
           System.out.println("order" + order);
                redisUtil.del(out_trade_no + "userId");

                if (userId == null || "0000".equals(order) || !"1".equals(order)){
                    String refundd = payFeign.refund(out_trade_no, trade_no, total_amount, "支付商品订单超时", "");
                    return refundd;
                }
                return null;
            }
        System.out.println("sssss"+s);
        return s;
    }
}
