package com.study.platform.config;

import com.study.platform.user.grpc.UserServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcConfig {

    @Bean
    public ManagedChannel userServiceChannel() {
        return ManagedChannelBuilder.forAddress("auth-service", 6565)  // DNS-имя сервиса
                .usePlaintext()
                .build();
    }


    @Bean
    public UserServiceGrpc.UserServiceBlockingStub userServiceBlockingStub(@Autowired ManagedChannel userServiceChannel) {
        return UserServiceGrpc.newBlockingStub(userServiceChannel);
    }
}
