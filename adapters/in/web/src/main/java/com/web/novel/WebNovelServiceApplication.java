package com.web.novel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class WebNovelServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebNovelServiceApplication.class, args);
    }
}
