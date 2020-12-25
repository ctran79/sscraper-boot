package com.ctran79.sscraperboot;

import com.ctran79.sscraperboot.article.model.Article;
import com.ctran79.sscraperboot.user.service.UserService;
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
