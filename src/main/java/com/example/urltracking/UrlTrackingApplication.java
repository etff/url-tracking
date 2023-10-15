package com.example.urltracking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class UrlTrackingApplication {

    public static void main(String[] args) {
        SpringApplication.run(UrlTrackingApplication.class, args);
    }

}
