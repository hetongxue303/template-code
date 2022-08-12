package com.hetongxue;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Description: 程序入口类
 * @ClassNmae: TemplateApplication
 * @Author: 何同学
 * @DateTime: 2022-08-12 12:28
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.hetongxue.system.mapper")
public class TemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(TemplateApplication.class, args);
    }

}