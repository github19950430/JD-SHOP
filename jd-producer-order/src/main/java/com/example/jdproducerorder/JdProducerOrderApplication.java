package com.example.jdproducerorder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.solr.SolrAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = SolrAutoConfiguration.class)
@EnableDiscoveryClient
@EnableSwagger2
@EnableScheduling//开启定时任务
@MapperScan("com.example.jdproducerorder.dao")
public class JdProducerOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(JdProducerOrderApplication.class, args);
    }

}

