package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}