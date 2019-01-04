package com.example.jdconsumerjudiciary.controller;


import com.alibaba.fastjson.JSON;
import com.example.jdconsumerjudiciary.feign.JudSelFegin;
import com.example.jdconsumerjudiciary.feign.TestFeign;
import com.example.jdconsumerjudiciary.rabbitmqUitl.RabbitConfig;
import com.example.jdconsumerjudiciary.uitl.RedisUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {


    @Resource
    private TestFeign feign;

    @Resource
    private RedisUtil redisUtil;
    @Resource
    private RabbitTemplate rabbitTemplate;
    @Resource
    private JudSelFegin judSelFegin;

    @RequestMapping("/payadd")
    public String pay(){
        String o =  rabbitTemplate.convertSendAndReceive(RabbitConfig.EXCHANGE_RAISE_A_PRICE, RabbitConfig.ADDPRICE, "拍卖,3,3") + "";
        System.out.println(o + "oooooooo");
        return o;
    }

    @RequestMapping("/asd")
    public String aa(){
        return feign.aa();
    }

    @RequestMapping("/redis")
    public String redis(){
        redisUtil.set("a","555");
        return (String) redisUtil.get("a");
    }

    @RequestMapping("/ddd")
    public String asd(){
        return JSON.toJSONString("6666");
    }

    @RequestMapping("/bbb")
    public String bbb(Integer WIDjudID){
        String selcash = judSelFegin.selcash(WIDjudID);
        return selcash;
    }
}
