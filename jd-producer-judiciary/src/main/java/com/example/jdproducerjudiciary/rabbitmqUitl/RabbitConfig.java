package com.example.jdproducerjudiciary.rabbitmqUitl;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    //触发加价接口   接收消费者消息生产者进行一次加价
    public static final String QUEUE_RAISE_A_PRICE = "QUEUE_RAISE_A_PRICE";
    //队列一的路由键
    public static final String ADDPRICE = "addprice";

    //拍卖加价付款
    public static final String QUEUE_PAYMENT = "QUEUE_PAYMENT";
    //队列二的路由键
    public static final String PAYMENT = "payment";

    //发送卡号名字去瀚文验证是否匹配
    public static final String QUEUE_SELNUMBER_NAME = "QUEUE_SELNUMBER_NAME";
    //监听瀚文发给我的队列
    public static final String QUEUE_HANWEN = "QUEUE_HANWEN";



    /** 消息交换机的名字*/
    //one
    public static final String EXCHANGE_RAISE_A_PRICE = "EXCHANGE_RAISE_A_PRICE";
    //two
    public static final String EXCHANGE_PAYMENT = "EXCHANGE_PAYMENT";

    //声明队列

    @Bean
    public Queue queue(){
        return new Queue(QUEUE_RAISE_A_PRICE,true,false,false);
    }
    @Bean
    public Queue queuepay(){
        return new Queue(QUEUE_PAYMENT,true,false,false);
    }
    @Bean
    public Queue queuesel(){
        return new Queue(QUEUE_SELNUMBER_NAME,true,false,false);
    }
    @Bean
    public Queue queuehw(){
        return new Queue(QUEUE_HANWEN,true,false,false);
    }



    //声明交换机
    @Bean
    public DirectExchange directExchangeUpPrice(){
        return new DirectExchange(EXCHANGE_RAISE_A_PRICE,true,false);
    }
    @Bean
    public DirectExchange directExchangePayMent(){
        return new DirectExchange(EXCHANGE_PAYMENT,true,false);
    }





    //队列交换机绑定 QUEUE_RAISE_A_PRICE 加价
    @Bean
    public Binding binding_price(){
        return BindingBuilder.bind(queue()).to(directExchangeUpPrice()).with(ADDPRICE);
    }
    //队列交换机绑定 QUEUE_PAYMENT 拍卖加价付款
    @Bean
    public Binding binding_payment(){
        return BindingBuilder.bind(queuepay()).to(directExchangePayMent()).with(PAYMENT);
    }

}
