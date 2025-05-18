package com.study.platform.service;

import com.study.interaction.dto.auth.AuthResponse;
import com.study.interaction.dto.auth.LoginRequest;
import com.study.interaction.dto.auth.RegisterRequest;
import com.study.interaction.dto.auth.TokenPair;

public interface LoginInformationService {

    void register(RegisterRequest registerRequest);

    TokenPair login(LoginRequest request);

    AuthResponse refreshAccessToken(String refreshTokenHash);

    void revokeRefreshToken(String token);
}
