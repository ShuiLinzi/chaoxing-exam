package com.shui.exam;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

//@ComponentScan(basePackages = {"com.*"})
//@ComponentScan(basePackages = {"com.shui.exam.mapper","com.shui.exam.config"})//扫描mapper,和swagger的配置类
public class ExamPersonApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExamPersonApplication.class, args);
    }
}