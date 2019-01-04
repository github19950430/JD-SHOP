package com.example.jdconsumerpay.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.jdconsumerpay.feign.PayFeign;
import com.example.jdconsumerpay.server.PayService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RestController
public class PayController {
    @Resource
    private PayService payService;

    @Resource
    private PayFeign payFeign;

    @RequestMapping(value = "/order",method = RequestMethod.GET,produces = "text/json;charset=utf-8")
    public String order(String out_trade_no, String trade_no, String total_amount, HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("*****");
        System.out.println("out_trade_no=" + out_trade_no + "trade_no=" + trade_no + "total_amount=" + total_amount);

        String payorder = payService.payorder(out_trade_no, trade_no, total_amount,request);

        if (payorder != null){
            System.out.println("payorder"+ payorder);

            JSONObject jsonObject = JSON.parseObject(payorder);
            String res = jsonObject.get("alipay_trade_refund_response").toString();
            JSONObject jsonObject1 = JSON.parseObject(res);
            String msg = jsonObject1.get("msg").toString();

            response.setContentType("text/html;charset=UTF-8");
            request.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
//                    response.sendRedirect("home-index.html");
            out.print("<script>alert('"+ "商品支付失败,已退款:" + msg + "');location='home-index.html';</script>");
            out.close();

            return null;
        }

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        //                    response.sendRedirect("home-index.html");
        out.print("<script>alert('" + "商品支付成功!');location='home-index.html';</script>");
        out.close();
        return null;
    }

    @RequestMapping(value = "/payyyyy")
    public String asd(String WIDtotal_amount,String WIDsubject, String WIDorderID,Integer WIDuserid){
       return payFeign.pay(WIDtotal_amount,WIDsubject,WIDorderID,WIDuserid);
    }
}
