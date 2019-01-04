package com.example.jdconsumerorder.feign;


import com.example.jdconsumerorder.feign.impl.OrderFeignImpl;
import com.example.jdproducerorder.entity.solr.OrderVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "jd-producer-order",fallback = OrderFeignImpl.class)
public interface OrderFeign {

    @GetMapping(value = "/search")
    String searchOrder(@RequestParam("userid") Integer userid,
                              @RequestParam("status") Integer status,
                              @RequestParam("index") Integer index) throws Exception;


    @PostMapping("/create")
    String create(@RequestBody OrderVo orderVo);
}
