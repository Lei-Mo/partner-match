package com.leimo.partnermatch;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.leimo.partnermatch.mapper")
public class PartnerMatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(PartnerMatchApplication.class, args);
    }

}
