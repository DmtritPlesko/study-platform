package com.study.platform.service.grpc;

import com.study.interaction.dto.user.InformationUser;
import com.study.interaction.exception.CourseNotFoundException;
import com.study.platform.user.grpc.UserServiceGrpc;
import com.study.platform.user.grpc.UserServiceOuterClass;
import io.grpc.StatusRuntimeException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthGrpcClient {

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
