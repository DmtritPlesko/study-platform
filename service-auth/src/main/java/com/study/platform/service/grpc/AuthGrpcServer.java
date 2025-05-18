package com.study.platform.service.grpc;

import com.google.protobuf.BoolValue;
import com.study.platform.entity.LoginInformation;
import com.study.platform.entity.UserRole;
import com.study.platform.repository.LoginInformationRepository;
import com.study.platform.user.grpc.UserServiceGrpc;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.Optional;

import static com.study.platform.user.grpc.UserServiceOuterClass.*;

@GrpcService
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthGrpcServer extends UserServiceGrpc.UserServiceImplBase {

    final LoginInformationRepository repository;

    @Override
    public void updateLoginInformationUser(UpdateUserInformationRequest request,
                                           StreamObserver<BoolValue> responseObserver) {

        validateUserExists(request.getUserId(), responseObserver)
                .ifPresent(user -> {
                    if (request.getEmail() != null) {
                        user.setEmail(request.getEmail());
                    }
                    if (request.getPassword() != null) {
                        user.setPassword(request.getPassword());
                    }
                    if(request.getRole() != null) {
                        user.setRole(UserRole.valueOf(request.getRole()));
                    }

                    repository.save(user);
                    responseObserver.onNext(BoolValue.of(true));
                    responseObserver.onCompleted();
                });
    }

    @Override
    public void deleteInformationUser(BaseUserInformationRequest request,
                                      StreamObserver<BoolValue> responseObserver) {

        validateUserExists(request.getUserId(), responseObserver)
                .ifPresent(user -> {
                    repository.deleteById(request.getUserId());
                    responseObserver.onNext(BoolValue.of(true));
                    responseObserver.onCompleted();
                });
    }

    @Override
    public void getInformationUser(BaseUserInformationRequest request,
                                   StreamObserver<GetUserInformationResponse> responseObserver) {

        validateUserExists(request.getUserId(), responseObserver)
                .ifPresent(user -> {
                    GetUserInformationResponse response = GetUserInformationResponse.newBuilder()
                            .setEmail(user.getEmail())
                            .setPassword(user.getPassword())
                            .setRole(user.getRole().name())
                            .build();

                    responseObserver.onNext(response);
                    responseObserver.onCompleted();
                });
    }

    @Override
    public void existUserById(BaseUserInformationRequest request,
                              StreamObserver<com.google.protobuf.BoolValue> responseObserver) {
    }


    private Optional<LoginInformation> validateUserExists(String userId, StreamObserver<?> responseObserver) {

        Optional<LoginInformation> user = repository.findById(userId);
        if (user.isEmpty()) {
            responseObserver.onError(Status.NOT_FOUND
                    .withDescription("Пользователь не найден")
                    .asRuntimeException());
        }
        return user;
    }

}
