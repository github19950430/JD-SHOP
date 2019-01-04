package com.example.jdproducergetcoupin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

@EnableEurekaClient
@SpringBootApplication
@MapperScan("com/example/jdproducergetcoupin/cn/dao")
public class JdProducerGetcoupinApplication {

    public static void main(String[] args) {
        SpringApplication.run(JdProducerGetcoupinApplication.class, args);
    }

}

