package com.example.jdconsumerorder.feign;

import com.example.jdconsumerorder.feign.impl.PayFeignImpl;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "jd-pay",fallback = PayFeignImpl.class)
public interface PayFeign {

    @RequestMapping(value = "pay",method = RequestMethod.POST,produces = "text/html;charset=utf-8")
    public String pay(@RequestParam("WIDtotal_amount") String WIDtotal_amount,
                      @RequestParam("WIDsubject") String WIDsubject,
                      @RequestParam("WIDorderID") String WIDorderID,
                      @RequestParam("WIDuserid") Integer WIDuserid);
}
