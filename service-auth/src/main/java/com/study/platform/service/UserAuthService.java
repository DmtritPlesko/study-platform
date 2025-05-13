package com.study.platform.service;

import com.study.interaction.auth.dto.AuthResponse;
import com.study.interaction.auth.dto.LoginRequest;
import com.study.interaction.auth.dto.RegisterRequest;

public interface UserAuthService {

    void register(RegisterRequest registerRequest);

    AuthResponse login(LoginRequest request);
}
