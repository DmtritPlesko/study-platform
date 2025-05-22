package com.study.platform.service;

import com.study.interaction.dto.auth.*;
import com.study.interaction.exception.ConflictException;
import com.study.interaction.exception.InvalidPasswordException;
import com.study.interaction.exception.UserNotFoundException;
import com.study.platform.auth.grpc.AuthServiceGrpc;
import com.study.platform.auth.grpc.AuthServiceOuterClass;
import com.study.platform.entity.LoginInformation;
import com.study.platform.entity.UserRole;
import com.study.platform.repository.LoginInformationRepository;
import com.study.platform.util.JwtUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginInformationServiceImpl implements LoginInformationService {

    final AuthServiceGrpc.AuthServiceBlockingStub authServiceBlockingStub;

    final LoginInformationRepository authRepository;

    final PasswordEncoder passwordEncoder;

    final JwtUtil jwtUtil;

    public void register(RegisterRequest registerRequest) {

        if (authRepository.findByEmail(registerRequest.getEmail()).isEmpty()) {
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
    public TokenPair login(LoginRequest request) {

        LoginInformation loginInformation = authRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("Пользователь с email - "
                        + request.getEmail() + " не зарегистрирован"));

        if (!passwordEncoder.matches(loginInformation.getPassword(), request.getPassword())) {
            throw new InvalidPasswordException("Неверный пароль для пользователя email - " + request.getEmail());
        }

        RefreshToken refreshToken = jwtUtil.generateRefreshToken();
        loginInformation.setRefreshTokenHash(refreshToken.getHash());
        loginInformation.setTokenExpire(Date.valueOf(LocalDate.now().plusDays(7)));

        authRepository.save(loginInformation);

        return new TokenPair(jwtUtil.generateAccessToken(loginInformation), refreshToken.getToken());

    }

    @Override
    public void revokeRefreshToken(String token) {

        authRepository.findByRefreshTokenHash(jwtUtil.generateHashByToken(token))
                .ifPresent(information -> {
                    information.setRefreshTokenHash(null);
                    information.setTokenExpire(null);
                    authRepository.save(information);
                });

    }

    @Override
    public AuthResponse refreshAccessToken(String refreshTokenHash) {
        return null;
    }

}
