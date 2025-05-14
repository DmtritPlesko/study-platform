package com.study.platform.service.grpc;

import com.study.interaction.user.dto.InformationUser;
import com.study.platform.user.grpc.UserServiceGrpc;
import com.study.platform.user.grpc.UserServiceOuterClass;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthGrpcClient {

    final UserServiceGrpc.UserServiceBlockingStub userServiceBlockingStub;

    public String deleteUser(String userId) {

        UserServiceOuterClass.BaseUserInformationRequest request =
                UserServiceOuterClass.BaseUserInformationRequest.newBuilder()
                        .setUserId(userId)
                        .build();

        UserServiceOuterClass.BaseUserInformationResponse response =
                userServiceBlockingStub
                        .deleteInformationUser(UserServiceOuterClass.BaseUserInformationRequest.newBuilder()
                        .setUserId(userId).build());

        return response.getStatus();
    }

    public String updateUser(String id, InformationUser informationUser) {

        UserServiceOuterClass.UpdateUserInformationRequest updateUserInformationRequest =
                checkFieldsUpdate(id, informationUser);

        UserServiceOuterClass.BaseUserInformationResponse response =
                userServiceBlockingStub.updateLoginInformationUser(updateUserInformationRequest);

        return response.getStatus();
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
