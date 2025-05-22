package com.study.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.study.platform",
        "com.study.platform.service.grpc"})
@EnableJpaRepositories
@EnableDiscoveryClient
public class UserServiceApplication{
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class,args);
    }
}