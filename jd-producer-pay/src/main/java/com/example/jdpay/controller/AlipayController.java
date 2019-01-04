package com.example.jdpay.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.jdpay.RabbitMQUtil.RabbitConfig;
import com.example.jdpay.alipay.*;
//import com.example.jdpay.feign.OrderFeign;
import com.example.jdpay.redisutil.RedisUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RestController
public class AlipayController {
    @Resource
    private RabbitTemplate rabbitTemplate;
    @Resource
    private RedisUtil redisUtil;
//    @Resource
//    private OrderFeign orderFeign;
//        @ResponseBody
        @RequestMapping(value = "close",method = RequestMethod.POST,produces = "text/html;charset=utf-8")
        public String close(String WIDTCout_trade_no, String WIDTCtrade_no) {
            Close c = new Close();
            String s = c.close(WIDTCout_trade_no, WIDTCtrade_no);
            return s;
        }
        // WIDtotal_amount 金额    WIDsubject主题    WIDorderID订单编号 WIDuserid 用户ID
        @RequestMapping(value = "pay",method = RequestMethod.POST,produces = "text/html;charset=utf-8")
        public String pay(@RequestParam("WIDtotal_amount") String WIDtotal_amount,@RequestParam("WIDsubject") String WIDsubject,@RequestParam("WIDorderID") String WIDorderID,@RequestParam("WIDuserid") Integer WIDuserid) {

            Pay p = new Pay();
            System.out.println(WIDsubject);
            /*SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String WIDout_trade_no = sdf.format(new Date());
            System.out.println("交易单号==="+WIDout_trade_no);//订单号*/
            String result = p.pay(WIDorderID,WIDtotal_amount,WIDsubject);
            if (result != null){
                redisUtil.set(WIDorderID + "userId",WIDuserid + "",60*20);
            }
            return result;
        }
//        @ResponseBody
        @RequestMapping(value = "notify_url",method = RequestMethod.POST,produces = "text/html;charset=utf-8")
        public String notify_url(String out_trade_no,String trade_no,String trade_status,HttpServletRequest request) {
            Map<String, String[]> requestParams = request.getParameterMap();
            Notify_url n = new Notify_url();
            String result = n.notify_url(requestParams, out_trade_no, trade_no, trade_status);
            System.out.println("notify_url"+result);

            return result;
        }
//        @ResponseBody
        @RequestMapping(value = "query",method = RequestMethod.GET,produces = "text/html;charset=utf-8")
        public String Query(String WIDTQout_trade_no, String WIDTQtrade_no) {
            Query q = new Query();
            String s = q.query(WIDTQout_trade_no, WIDTQtrade_no);
            return s;
        }

//        @ResponseBody
        @RequestMapping(value = "refund",method = RequestMethod.POST,produces = "text/html;charset=utf-8")
        public String refund(@RequestParam("WIDTRout_trade_no") String WIDTRout_trade_no,
                             @RequestParam("WIDTRtrade_no") String WIDTRtrade_no,
                             @RequestParam("WIDTRrefund_amount") String WIDTRrefund_amount,
                             @RequestParam("WIDTRrefund_reason") String WIDTRrefund_reason,
                             @RequestParam("WIDTRout_request_no") String WIDTRout_request_no) {
            Refund r = new Refund();
            String s = r.refund(WIDTRout_trade_no, WIDTRtrade_no,WIDTRrefund_amount,WIDTRrefund_reason,WIDTRout_request_no);
            return s;
        }

//        @ResponseBody
        @RequestMapping(value = "refundQuery",method = RequestMethod.POST,produces = "text/html;charset=utf-8")
        public String refundQuery(String WIDRQout_trade_no, String WIDRQtrade_no, String WIDRQout_request_no) {
            RefundQuery r = new RefundQuery();
            String s = r.refundQuery(WIDRQout_trade_no,WIDRQtrade_no,WIDRQout_request_no);
            return s;
        }

//        @ResponseBody
        @RequestMapping(value = "return_url",method = RequestMethod.POST,produces = "text/html;charset=utf-8")
        public String alipayReturn_url(@RequestParam("out_trade_no") String out_trade_no,
                                       @RequestParam("trade_no") String trade_no,
                                       @RequestParam("total_amount") String total_amount,
                                       HttpServletRequest request) throws IOException {
            System.out.println("out_trade_no=" + out_trade_no + "trade_no=" + trade_no + "total_amount=" + total_amount);
            Map<String, String[]> requestParams = request.getParameterMap();
            Return_url r = new Return_url();
            String s = r.return_url(requestParams,out_trade_no,trade_no,total_amount);
            /*if (!s.equals("验签失败")) {
                System.out.println("000"+123);
//                rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_PAYOUT,out_trade_no + "," + total_amount);
                String userId = (String) redisUtil.get(out_trade_no + "userId");
                long time = System.currentTimeMillis();  //时间戳 毫秒
                *//*String order = orderFeign.noPayment2payment(out_trade_no, Integer.parseInt(userId),trade_no , time + "");

                redisUtil.del(out_trade_no + "userId");

                if (userId == null || "0000".equals(order) || !"1".equals(order)){
                    String refundd = refund(out_trade_no, trade_no, total_amount, "支付商品订单超时", "");

                    JSONObject jsonObject = JSON.parseObject(refundd);
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
                }*//*
                    response.setContentType("text/html;charset=UTF-8");
                    request.setCharacterEncoding("UTF-8");
                    PrintWriter out = response.getWriter();
    //                    response.sendRedirect("home-index.html");
                    out.print("<script>alert('" + "商品支付成功!');location='home-index.html';</script>");
                    out.close();
                return null;
            }*/

            return s;
        }



}
