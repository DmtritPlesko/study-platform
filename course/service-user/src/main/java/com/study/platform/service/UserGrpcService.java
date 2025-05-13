package com.study.platform.service;

import com.study.platform.auth.grpc.AuthServiceGrpc;
import com.study.platform.auth.grpc.AuthServiceOuterClass;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class UserGrpcService extends AuthServiceGrpc.AuthServiceImplBase {

    @Override
    public void registerUser(
            AuthServiceOuterClass.UserRequest request,
            StreamObserver<AuthServiceOuterClass.UserResponse> responseObserver) {



    }
}
