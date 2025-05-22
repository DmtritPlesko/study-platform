package com.study.platform.config;

import com.study.platform.auth.grpc.AuthServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcConfig {
    @Bean
    public ManagedChannel authServiceChannel() {
        return ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext() // Для разработки (в production используйте SSL/TLS)
                .build();
    }

    @Bean
    public AuthServiceGrpc.AuthServiceBlockingStub authServiceBlockingStub(ManagedChannel channel) {
        return AuthServiceGrpc.newBlockingStub(channel);
    }
}
