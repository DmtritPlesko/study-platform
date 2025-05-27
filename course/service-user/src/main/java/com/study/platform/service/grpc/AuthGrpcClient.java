package com.study.platform.service.grpc;

import com.study.interaction.dto.auth.LoginRequest;
import com.study.interaction.dto.user.InformationUser;
import com.study.interaction.exception.CourseNotFoundException;
import com.study.platform.user.grpc.UserServiceGrpc;
import com.study.platform.user.grpc.UserServiceOuterClass;
import io.grpc.StatusRuntimeException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthGrpcClient {

    @GrpcClient("user-service")
    final UserServiceGrpc.UserServiceBlockingStub userServiceBlockingStub;

    public void deleteUser(String userId) {

        try {
            userServiceBlockingStub
                    .deleteInformationUser(UserServiceOuterClass.BaseUserInformationRequest.newBuilder()
                            .setUserId(userId).build());
        } catch (StatusRuntimeException e) {
            throw new CourseNotFoundException(e.getMessage());
        }

    }

    public void updateUser(String id, InformationUser informationUser) {

        UserServiceOuterClass.UpdateUserInformationRequest updateUserInformationRequest =
                checkFieldsUpdate(id, informationUser);

        try {
            userServiceBlockingStub.updateLoginInformationUser(updateUserInformationRequest);
        } catch (StatusRuntimeException e) {
            throw new CourseNotFoundException(e.getMessage());
        }

    }

    public String getUserIdByUserToken(String token) {

        UserServiceOuterClass.BaseUserInformationRequest request =
                UserServiceOuterClass.BaseUserInformationRequest.newBuilder()
                        .setUserId(token)
                        .build();

        UserServiceOuterClass.BaseUserInformationResponse response =
                userServiceBlockingStub.getUserIdByToken(request);

        return response.getUserId();
    }

    public LoginRequest getUserLoginInfo (String id) {

        UserServiceOuterClass.BaseUserInformationRequest request =
                UserServiceOuterClass.BaseUserInformationRequest.newBuilder()
                        .setUserId(id)
                        .build();

        UserServiceOuterClass.GetUserInformationResponse response =
                userServiceBlockingStub.getInformationUser(request);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(response.getEmail());
        loginRequest.setPassword(response.getPassword());

        return loginRequest;
    }

    private UserServiceOuterClass.UpdateUserInformationRequest checkFieldsUpdate(
            String userId,
            InformationUser informationUser
    ) {
        UserServiceOuterClass.UpdateUserInformationRequest.Builder builder =
                UserServiceOuterClass.UpdateUserInformationRequest.newBuilder()
                        .setUserId(userId);

        if (informationUser.getEmail() != null) {
            builder.setEmail(informationUser.getEmail());
        }
        if (informationUser.getPassword() != null) {
            builder.setPassword(informationUser.getPassword());
        }
        if (informationUser.getRole() != null) {
            builder.setRole(informationUser.getRole());
        }

        return builder.build();
    }
}
