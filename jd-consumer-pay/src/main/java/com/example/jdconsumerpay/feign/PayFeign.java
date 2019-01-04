package com.example.jdconsumerpay.feign;

import com.example.jdconsumerpay.feign.impl.PayFeignImpl;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@FeignClient(name = "jd-producer-pay",fallback = PayFeignImpl.class)
public interface PayFeign {
    //验证支付数据
    @RequestMapping(value = "return_url",method = RequestMethod.POST,produces = "text/html;charset=utf-8")
    String alipayReturn_url(@RequestParam("out_trade_no") String out_trade_no,
                                   @RequestParam("trade_no") String trade_no,
                                   @RequestParam("total_amount") String total_amount,
                                   HttpServletRequest request) throws IOException;
    //退款接口
    @RequestMapping(value = "refund",method = RequestMethod.POST,produces = "text/html;charset=utf-8")
    String refund(@RequestParam("WIDTRout_trade_no") String WIDTRout_trade_no,
                         @RequestParam("WIDTRtrade_no") String WIDTRtrade_no,
                         @RequestParam("WIDTRrefund_amount") String WIDTRrefund_amount,
                         @RequestParam("WIDTRrefund_reason") String WIDTRrefund_reason,
                         @RequestParam("WIDTRout_request_no") String WIDTRout_request_no);

    //支付
    @RequestMapping(value = "pay",method = RequestMethod.POST,produces = "text/html;charset=utf-8")
    public String pay(@RequestParam("WIDtotal_amount") String WIDtotal_amount,
                      @RequestParam("WIDsubject") String WIDsubject,
                      @RequestParam("WIDorderID") String WIDorderID,
                      @RequestParam("WIDuserid") Integer WIDuserid);
}
