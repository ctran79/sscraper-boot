package com.ctran79.sscraperboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SscraperBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SscraperBootApplication.class, args);
    }
}
