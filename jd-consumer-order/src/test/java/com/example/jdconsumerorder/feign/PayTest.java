package com.example.jdconsumerorder.feign;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PayTest {

    @Resource
    private PayFeign pay;

    @Resource
    private OrderFeign feign;

    @Test
    public void pay() {
//        pay.pay("8974.0",)
    }
}