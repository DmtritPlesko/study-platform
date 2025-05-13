package com.study.platform.controller;

import com.study.interaction.auth.dto.JwtResponse;
import com.study.interaction.auth.dto.LoginRequest;
import com.study.interaction.auth.dto.RegisterRequest;
import com.study.platform.service.UserAuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthController {

    final UserAuthService service;

    @PostMapping(path = "/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        service.register(registerRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body("Пользователь создан");
    }

    @PostMapping(path = "/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest loginRequest) {


        return null;
    }

}
