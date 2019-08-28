package com.ascending.estate.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication(scanBasePackages = {"com.ascending.estate"})
@ServletComponentScan(basePackages = {"com.ascending.estate.filter"})
public class AppInitializer {
    public static void main(String[] args) {
        SpringApplication.run(AppInitializer.class, args);
    }
}
