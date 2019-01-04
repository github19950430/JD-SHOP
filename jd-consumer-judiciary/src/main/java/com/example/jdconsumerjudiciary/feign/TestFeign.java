package com.example.jdconsumerjudiciary.feign;


import com.example.jdconsumerjudiciary.feign.impl.TestFeginHy;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "jd-producer-judiciary",fallback = TestFeginHy.class)
public interface TestFeign {

    @RequestMapping("/aa")
    public String aa();

}
