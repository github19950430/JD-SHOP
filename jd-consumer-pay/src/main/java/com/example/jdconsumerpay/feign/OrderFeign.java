package com.example.jdconsumerpay.feign;


import com.example.jdconsumerpay.feign.impl.OrderFeignImpl;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "jd-producer-order",fallback = OrderFeignImpl.class)
public interface OrderFeign {
    //支付成功后调用订单
    @PostMapping("/noPayment2payment")  // orderNo订单编号 userId 用户ID payNo 交易单号   payTime 支付时间
    String noPayment2payment(@RequestParam("orderNo") String orderNo,
                             @RequestParam("userId") Integer userId,
                             @RequestParam("payNo") String payNo,
                             @RequestParam("payTime") String payTime);

    /*@PostMapping("/noPayment2payment")
    String noPayment2payment(@RequestParam("orderNo") String orderNo,
                             @RequestParam("userId") Integer userId,
                             @RequestParam("payNo") String payNo,
                             @RequestParam("payTime") String payTime);*/
}
