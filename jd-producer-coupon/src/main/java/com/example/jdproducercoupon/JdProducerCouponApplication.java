package com.example.jdproducercoupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

@EnableEurekaClient
@SpringBootApplication
@MapperScan("com/example/jdproducercoupon/cn/mapper")
public class JdProducerCouponApplication {

    public static void main(String[] args) {
        SpringApplication.run(JdProducerCouponApplication.class, args);
    }
}

