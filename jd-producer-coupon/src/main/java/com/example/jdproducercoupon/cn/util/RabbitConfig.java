package com.example.jdproducercoupon.cn.util;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    /**
     * 〈一句话功能简述〉<br>
     * 优惠券领取，通知减库存
     *
     * @author //TODO RanHaoHao
     * @date 2018/12/25 8:35
     */
    public static final String QUEUE_MINUS_INVENTORY = "QUEUE_MINUS_INVENTORY";
    /**
     * 〈一句话功能简述〉<br>
     * 监听减库存返回
     *
     * @author //TODO RanHaoHao
     * @date 2018/12/26 8:40
     */
    public static final String QUEUE_MINUS_INVENTORY_RETURN = "QUEUE_MINUS_INVENTORY_RETURN";
    //发送卡号名字去瀚文验证是否匹配
    public static final String QUEUE_SELNUMBER_NAME = "QUEUE_SELNUMBER_NAME";
    //监听瀚文发给我的队列
    public static final String QUEUE_HANWEN = "QUEUE_HANWEN";


    public static final String QUEUE_RAISE_A_PRICE = "QUEUE_RAISE_A_PRICE";

    @Bean
    public Queue queue11() {
        return new Queue(QUEUE_RAISE_A_PRICE, true, false, false);
    }
    @Bean
    public Queue queue() {
        return new Queue(QUEUE_MINUS_INVENTORY, true, false, false);
    }

    @Bean
    public Queue queueBa() {
        return new Queue(QUEUE_MINUS_INVENTORY_RETURN, true, false, false);
    }

    @Bean
    public Queue queuesel() {
        return new Queue(QUEUE_SELNUMBER_NAME, true, false, false);
    }

    @Bean
    public Queue queuehw() {
        return new Queue(QUEUE_HANWEN, true, false, false);
    }
}
