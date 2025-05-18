package com.study.platform.service;

import com.study.interaction.exception.UserNotFoundException;
import com.study.interaction.dto.user.InformationUser;
import com.study.interaction.dto.user.UserDto;
import com.study.platform.model.User;
import com.study.platform.repositroy.UserRepository;
import com.study.platform.service.grpc.AuthGrpcClient;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {

    final UserRepository repository;
    final AuthGrpcClient authGrpcClient;

    @Override
    public UserDto getAllInformationUser(String username) {
        return null;
    }

    @Override
    public UserDto getUser(String userId) {

        User user = repository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден"));

        return UserDto.builder()
                .username(user.getUsername())
                .groupName(user.getGroupName())
                .build();
    }

    @Override
    public UserDto updateUser(String id, InformationUser userInformation) {

        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Пользователь: "
                        + userInformation.getUsername() + " не найден"));


        authGrpcClient.updateUser(id, userInformation);

        if (!user.getUsername().equals(userInformation.getUsername())) {
            user.setUsername(userInformation.getUsername());
        }

        if (!user.getGroupName().equals(userInformation.getGroupName())) {
            user.setGroupName(userInformation.getGroupName());
        }

        return UserDto.builder()
                .groupName(user.getGroupName())
                .username(user.getUsername())
                .build();
    }

    @Override
    public void deleteUser(String username) {

        User user = repository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Пользователь: " + username + " не найден"));

        authGrpcClient.deleteUser(user.getId());

        repository.deleteById(user.getId());
    }

}
