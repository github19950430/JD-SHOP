package com.example.jdproducercouponinfo.cn.util;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    //触发汇款接口   发送消息给瀚文  进行金额变更  单笔汇款
    public static final String QUEUE_REMITTANCE = "QUEUE_REMITTANCE";
    //批量汇款
    public static final String QUEUE_BATCH = "QUEUE_BATCH";
    //发送卡号名字去瀚文验证是否匹配
    public static final String QUEUE_SELNUMBER_NAME = "QUEUE_SELNUMBER_NAME";
    //监听瀚文发给我的队列
    public static final String QUEUE_HANWEN = "QUEUE_HANWEN";
    @Bean
    public Queue queue(){
        return new Queue(QUEUE_REMITTANCE,true,false,false);
    }
    @Bean
    public Queue queueBa(){
        return new Queue(QUEUE_BATCH,true,false,false);
    }
    @Bean
    public Queue queuesel(){
        return new Queue(QUEUE_SELNUMBER_NAME,true,false,false);
    }
    @Bean
    public Queue queuehw(){
        return new Queue(QUEUE_HANWEN,true,false,false);
    }
}
