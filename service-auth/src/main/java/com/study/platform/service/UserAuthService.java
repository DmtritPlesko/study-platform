package com.study.platform.service;

import com.study.platform.auth.dto.RegisterRequest;
import com.study.platform.entity.LoginInformation;
import com.study.platform.entity.User;
import com.study.platform.exception.ConflictException;
import com.study.platform.repository.UserAuthRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserAuthService {

    final UserAuthRepository authRepository;

    public String register(RegisterRequest registerRequest) {
        if(authRepository.isExistUserByEmail(registerRequest.getEmail())) {
            throw new ConflictException("Пользователь с email - " + registerRequest.getEmail() + " уже существует");
        }


        LoginInformation user = LoginInformation.builder()
                .active(true)
                .email(registerRequest.getEmail())
                .role(registerRequest.getRole())
                .password(registerRequest.getPassword())
                .build();

        authRepository.save(user);
    }

}
