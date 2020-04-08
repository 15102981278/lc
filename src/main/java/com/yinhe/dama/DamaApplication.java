package com.yinhe.dama;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan(basePackages = {"com.yinhe.dama.mapper"})
public class DamaApplication {

    public static void main(String[] args) {
        SpringApplication.run(DamaApplication.class, args);
    }

}
