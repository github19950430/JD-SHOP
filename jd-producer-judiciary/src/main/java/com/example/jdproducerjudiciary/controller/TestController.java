package com.example.jdproducerjudiciary.controller;

import com.example.jdproducerjudiciary.rabbitmqUitl.RabbitConfig;
import com.example.jdproducerjudiciary.uitl.RedisUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
@RestController
public class TestController {


    @Resource
    private RedisUtil redisUtil;
    @Resource
    private RabbitTemplate rabbitTemplate;


    @RequestMapping("/aa")
    public String aa(){
        redisUtil.set("a","123");
        Object a = redisUtil.get("a");
        System.out.println((String) a);
        return "123";
    }
    @RequestMapping("/mq")
    public void mq(){
        System.out.println("000");
        Object ceshi = rabbitTemplate.convertSendAndReceive(RabbitConfig.EXCHANGE_RAISE_A_PRICE,RabbitConfig.ADDPRICE, "测试");
        System.out.println("*************" + ceshi);
    }

    @RequestMapping("/liuliuliu")
    public String bbb(Integer id){
        System.out.println(id);
        return "6666666" + id;
    }

}
