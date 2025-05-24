package com.study.platform.config;

import com.study.platform.auth.grpc.AuthServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcConfig {

    @Bean(name = "authServiceChannel")
    public ManagedChannel authServiceChannel() {
        return ManagedChannelBuilder.forAddress("user-service", 9090)  // Порт AuthService
                .usePlaintext()
                .build();
    }

    @Bean
    public AuthServiceGrpc.AuthServiceBlockingStub authServiceBlockingStub(
            @Autowired ManagedChannel authServiceChannel) {
        return AuthServiceGrpc.newBlockingStub(authServiceChannel);
    }
}
