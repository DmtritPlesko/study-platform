package com.study.platform.service;

import com.study.interaction.auth.dto.AuthResponse;
import com.study.interaction.auth.dto.LoginRequest;
import com.study.interaction.auth.dto.RegisterRequest;
import com.study.interaction.auth.dto.TokenPair;

public interface UserAuthService {

    void register(RegisterRequest registerRequest);

    TokenPair login(LoginRequest request);

    AuthResponse refreshAccessToken(String refreshTokenHash);

    void revokeRefreshToken(String token);
}
