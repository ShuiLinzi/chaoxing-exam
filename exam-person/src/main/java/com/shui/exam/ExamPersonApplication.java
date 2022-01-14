package com.shui.exam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.shui"})
@ComponentScan(basePackages = {"com.shui.exam.mapper"})
public class ExamPersonApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExamPersonApplication.class, args);
    }
}
