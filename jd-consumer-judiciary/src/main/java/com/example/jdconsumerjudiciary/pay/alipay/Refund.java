package com.example.jdconsumerjudiciary.pay.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.example.jdconsumerjudiciary.pay.payutil.AlipayConfig;

public class Refund {
    /**
     *
     * @param WIDTRout_trade_no 商户订单号，商户网站订单系统中唯一订单号
     * @param WIDTRtrade_no 支付宝交易号
     * @param WIDTRrefund_amount 需要退款的金额，该金额不能大于订单金额，必填
     * @param WIDTRrefund_reason 退款的原因说明
     * @param WIDTRout_request_no 标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传
     * @return
     */
    public String refund(String WIDTRout_trade_no,String WIDTRtrade_no,String WIDTRrefund_amount,String WIDTRrefund_reason,String WIDTRout_request_no){
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();

        /*//商户订单号，商户网站订单系统中唯一订单号
        String out_trade_no = new String(request.getParameter("WIDTRout_trade_no").getBytes("ISO-8859-1"),"UTF-8");
        //支付宝交易号
        String trade_no = new String(request.getParameter("WIDTRtrade_no").getBytes("ISO-8859-1"),"UTF-8");
        //请二选一设置
        //需要退款的金额，该金额不能大于订单金额，必填
        String refund_amount = new String(request.getParameter("WIDTRrefund_amount").getBytes("ISO-8859-1"),"UTF-8");
        //退款的原因说明
        String refund_reason = new String(request.getParameter("WIDTRrefund_reason").getBytes("ISO-8859-1"),"UTF-8");
        //标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传
        String out_request_no = new String(request.getParameter("WIDTRout_request_no").getBytes("ISO-8859-1"),"UTF-8");
        */
        alipayRequest.setBizContent("{\"out_trade_no\":\""+ WIDTRout_trade_no +"\","
                + "\"trade_no\":\""+ WIDTRtrade_no +"\","
                + "\"refund_amount\":\""+ WIDTRrefund_amount +"\","
                + "\"refund_reason\":\""+ WIDTRrefund_reason +"\","
                + "\"out_request_no\":\""+ WIDTRout_request_no +"\"}");

        //请求
        String result = null;
        try {
            result = alipayClient.execute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return result;
    }
}
