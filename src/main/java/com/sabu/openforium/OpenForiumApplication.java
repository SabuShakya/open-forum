package com.sabu.openforium;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.sabu")
public class OpenForiumApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenForiumApplication.class, args);
    }

}
