package com.example.jdproducerjudiciary;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.solr.SolrAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = SolrAutoConfiguration.class)
@EnableScheduling//boot开启定时任务注解
@EnableDiscoveryClient
@MapperScan("com.example.jdproducerjudiciary.dao")
public class JdProducerJudiciaryApplication {

    public static void main(String[] args) {
        SpringApplication.run(JdProducerJudiciaryApplication.class, args);
    }

}

