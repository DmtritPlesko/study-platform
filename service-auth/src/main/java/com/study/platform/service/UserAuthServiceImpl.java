package com.study.platform.service;

import com.study.interaction.auth.dto.AuthResponse;
import com.study.interaction.auth.dto.LoginRequest;
import com.study.interaction.auth.dto.RegisterRequest;
import com.study.platform.auth.grpc.AuthServiceGrpc;
import com.study.platform.auth.grpc.AuthServiceOuterClass;
import com.study.platform.entity.LoginInformation;
import com.study.platform.entity.UserRole;
import com.study.platform.exception.ConflictException;
import com.study.platform.exception.InvalidPasswordException;
import com.study.platform.exception.UserNotFoundException;
import com.study.platform.repository.UserAuthRepository;
import com.study.platform.util.JwtUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserAuthServiceImpl implements UserAuthService {

    final AuthServiceGrpc.AuthServiceBlockingStub authServiceBlockingStub;

    final UserAuthRepository authRepository;

    final PasswordEncoder passwordEncoder;

    final JwtUtil jwtUtil;

    public void register(RegisterRequest registerRequest) {

        if (authRepository.isExistUserByEmail(registerRequest.getEmail())) {
            throw new ConflictException("Пользователь с email - " + registerRequest.getEmail() + " уже существует");
        }

        AuthServiceOuterClass.UserRequest userRequest = AuthServiceOuterClass.UserRequest.newBuilder()
                .setGroupName(registerRequest.getNameGroup())
                .setUserName(registerRequest.getUsername())
                .build();

        LoginInformation user = LoginInformation.builder()
                .id(authServiceBlockingStub.registerUser(userRequest).getUserId())
                .active(true)
                .email(registerRequest.getEmail())
                .role(UserRole.valueOf(registerRequest.getRole()))
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .build();

        authRepository.save(user);
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        LoginInformation loginInformation = authRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("Пользователь с email - "
                        + request.getEmail() + " не зарегистрирован"));

        if (!passwordEncoder.matches(loginInformation.getPassword(), request.getPassword())) {
            throw new InvalidPasswordException("Неверный пароль для пользователя email - " + request.getEmail());
        }

        String token = jwtUtil.generateToken(loginInformation);

        return new AuthResponse(token);

    }

}
