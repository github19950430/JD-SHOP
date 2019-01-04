package com.example.jdpay.controller;

import com.example.jdpay.RabbitMQUtil.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {

    @Resource
    private RabbitTemplate rabbitTemplate;


    @RequestMapping("/queue")
    public void queue(){
        Object o = rabbitTemplate.convertSendAndReceive(RabbitConfig.EXCHANGE_RAISE_A_PRICE, RabbitConfig.ADDPRICE, "123");
        System.out.println(o);
    }


    @RabbitListener(queues = RabbitConfig.QUEUE_PAYOUT)
    public void aa(String msg){
        System.out.println(msg);
    }
}
