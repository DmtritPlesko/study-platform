package com.study.platform.service.grpc;

import com.study.platform.auth.grpc.AuthServiceGrpc;
import com.study.platform.auth.grpc.AuthServiceOuterClass;
import com.study.platform.model.User;
import com.study.platform.repositroy.UserRepository;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class UserGrpcService extends AuthServiceGrpc.AuthServiceImplBase {

    final UserRepository repository;

    @Override
    public void registerUser(
            AuthServiceOuterClass.UserRequest request,
            StreamObserver<AuthServiceOuterClass.UserResponse> responseObserver) {

        User user = User.builder()
                .groupName(request.getGroupName())
                .username(request.getUserName())
                .build();

        AuthServiceOuterClass.UserResponse response = AuthServiceOuterClass.UserResponse.newBuilder()
                .setUserId(repository.save(user).getId())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
