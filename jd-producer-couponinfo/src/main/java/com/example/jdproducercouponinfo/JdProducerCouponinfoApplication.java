package com.example.jdproducercouponinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;


/**
 * @author RanHaoHao
 */
@EnableEurekaClient
@SpringBootApplication
@MapperScan("com/example/jdproducercouponinfo/cn/mapper")
public class JdProducerCouponinfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JdProducerCouponinfoApplication.class, args);
    }

}

