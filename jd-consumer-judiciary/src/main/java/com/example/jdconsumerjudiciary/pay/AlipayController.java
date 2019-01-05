package com.example.jdconsumerjudiciary.pay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.jdconsumerjudiciary.entity.Cashdeposit;
import com.example.jdconsumerjudiciary.feign.JudSelFegin;
import com.example.jdconsumerjudiciary.pay.alipay.*;
import com.example.jdconsumerjudiciary.service.JudselService;
import com.example.jdconsumerjudiciary.uitl.RedisUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RestController
public class AlipayController {
    @Resource
    private JudselService judselService;
    @Resource
    private JudSelFegin judSelFegin;
    @Resource
    private RedisUtil redisUtil;
//        @ResponseBody
        @RequestMapping(value = "close",method = RequestMethod.POST,produces = "text/html; charset=utf-8")
        public String close(String WIDTCout_trade_no, String WIDTCtrade_no) {
            Close c = new Close();
            String s = c.close(WIDTCout_trade_no, WIDTCtrade_no);
            return s;
        }

//        @ResponseBody //    WIDsubject主题    WIDjudID商品ID  WIDuserid用户ID   交保证金
        @RequestMapping(value = "pay",method = RequestMethod.GET,produces = "text/html; charset=utf-8")
        public String pay(@RequestParam("WIDsubject") String WIDsubject,@RequestParam("WIDjudID") Integer WIDjudID,@RequestParam("WIDuserid") Integer WIDuserid) {
            String selcash = judSelFegin.selcash(WIDjudID);//根据商品ID查询该商品的保证金
            if ("0000".equals(selcash) || "404".equals(selcash))
                return "服务中断请重试!或没有该商品!1";
            Cashdeposit cashdeposit = new Cashdeposit();
            cashdeposit.setCashShopid(WIDjudID);
            cashdeposit.setCashUser(WIDuserid);
            cashdeposit.setCashType(1);
            String Cash = judSelFegin.selectOnebao(cashdeposit);//根据用户ID 和商品ID 还有 商品类型ID 查询保证金表里有没有保证金
            System.out.println("selcash=" + selcash);
            System.out.println(Cash + "===Cash");
            if ("0000".equals(Cash))
                return "服务中断请重试!或没有该商品!2";

            if (selcash.equals(Cash))
                return "您已交过该商品的保证金了";
            Pay p = new Pay();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String WIDout_trade_no = sdf.format(new Date());
            String replace = selcash.replace(",", "");

            String result = p.pay(WIDout_trade_no,replace,WIDsubject,WIDjudID,WIDuserid);
            if (result != null){
                redisUtil.set(WIDout_trade_no + "judid",WIDjudID + "",60);
                redisUtil.set(WIDout_trade_no + "userid",WIDuserid + "",60);
            }
            return result;
        }

//        @ResponseBody
        @RequestMapping(value = "notify_url",method = RequestMethod.POST,produces = "text/html; charset=utf-8")
        public String notify_url(String out_trade_no,String trade_no,String trade_status,HttpServletRequest request) {
            Map<String, String[]> requestParams = request.getParameterMap();
            Notify_url n = new Notify_url();
            String result = n.notify_url(requestParams, out_trade_no, trade_no, trade_status);
            System.out.println("notify_url"+result);

            return result;
        }
//        @ResponseBody
        @RequestMapping(value = "query",method = RequestMethod.GET,produces = "text/html; charset=utf-8")
        public String Query(String WIDTQout_trade_no, String WIDTQtrade_no) {
            Query q = new Query();
            String s = q.query(WIDTQout_trade_no, WIDTQtrade_no);
            return s;
        }

//        @ResponseBody  退款接口
        @RequestMapping(value = "refund",method = RequestMethod.GET,produces = "text/html; charset=utf-8")
        public String refund(String WIDTRout_trade_no, String WIDTRtrade_no, String WIDTRrefund_amount, String WIDTRrefund_reason, String WIDTRout_request_no) {
            Refund r = new Refund();
            String s = r.refund(WIDTRout_trade_no, WIDTRtrade_no,WIDTRrefund_amount,WIDTRrefund_reason,WIDTRout_request_no);
            return s;
        }

//        @ResponseBody
        @RequestMapping(value = "refundQuery",method = RequestMethod.POST,produces = "text/html; charset=utf-8")
        public String refundQuery(String WIDRQout_trade_no, String WIDRQtrade_no, String WIDRQout_request_no) {
            RefundQuery r = new RefundQuery();
            String s = r.refundQuery(WIDRQout_trade_no,WIDRQtrade_no,WIDRQout_request_no);
            return s;
        }
//        @ResponseBody
        @RequestMapping(value = "return_url",method = RequestMethod.GET,produces = "text/html; charset=utf-8")
        public String alipayReturn_url(String out_trade_no, String trade_no, String total_amount,HttpServletRequest request,HttpServletResponse response) throws IOException {
            System.out.println("out_trade_no=" + out_trade_no + "trade_no=" + trade_no + "total_amount=" + total_amount);
            Map<String, String[]> requestParams = request.getParameterMap();
            Return_url r = new Return_url();
            String s = r.return_url(requestParams,out_trade_no,trade_no,total_amount);
            if (!s.equals("验签失败")) {
                Integer judid = Integer.parseInt((String) redisUtil.get(out_trade_no + "judid"));
                Integer userid = Integer.parseInt( (String)redisUtil.get(out_trade_no + "userid"));

                String addcash = judselService.addcash(userid, judid, out_trade_no);

                redisUtil.del(out_trade_no + "judid");
                redisUtil.del(out_trade_no + "userid");

                System.out.println("total_amount==" + total_amount);
                if (judid == null || userid == null || addcash == null || "0000".equals(addcash)) {
                    String refundd = refund(out_trade_no, trade_no, total_amount, "支付保证金超时", "");
                    JSONObject jsonObject = JSON.parseObject(refundd);
                    String res = jsonObject.get("alipay_trade_refund_response").toString();
                    JSONObject jsonObject1 = JSON.parseObject(res);
                    String msg = jsonObject1.get("msg").toString();

                    response.setContentType("text/html;charset=UTF-8");
                    request.setCharacterEncoding("UTF-8");
                    PrintWriter out = response.getWriter();
                    out.print("<script>alert('"+ "您的保证金支付超时已退款:" + msg + "');location='seckill-item.html?\"" + judid + "';</script>");
//                    response.sendRedirect("seckill-item.html?" + judid);
                    out.close();

                    return null;
                }

                response.setContentType("text/html;charset=UTF-8");
                request.setCharacterEncoding("UTF-8");
                PrintWriter out = response.getWriter();
                response.sendRedirect("seckill-item.html?" + judid);
                out.close();
                return null;
            }
            return s;
        }
}
