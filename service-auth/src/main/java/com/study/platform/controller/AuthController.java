package com.study.platform.controller;

import com.study.interaction.dto.auth.AuthResponse;
import com.study.interaction.dto.auth.LoginRequest;
import com.study.interaction.dto.auth.RegisterRequest;
import com.study.interaction.dto.auth.TokenPair;
import com.study.platform.controller.documentation.CreateUserDock;
import com.study.platform.controller.documentation.LoginUserDock;
import com.study.platform.service.LoginInformationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
@RequestMapping(path = "api/v1/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Tag(name = "Auth", description = "Управление регистрацией пользователей")
public class AuthController {

    final LoginInformationService service;

    @CreateUserDock
    @PostMapping(path = "/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> register(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Данные для создания пользователя",
                    required = true
            )
            @RequestBody @Valid RegisterRequest registerRequest) {
        service.register(registerRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body("Пользователь создан");
    }


    @LoginUserDock
    @PostMapping(path = "/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest,
                                              HttpServletResponse response) {

        TokenPair tokenPair = service.login(loginRequest);

        ResponseCookie refreshCookie = ResponseCookie.from("refresh_token", tokenPair.getRefreshToken())
                .httpOnly(true)
                .secure(true)
                .sameSite("Strict")
                .path("api/v1/auth/refresh")
                .maxAge(Duration.ofDays(30))
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, refreshCookie.toString());
        return ResponseEntity.ok(new AuthResponse(tokenPair.getAccessToken()));
    }

    @PostMapping(path = "/refresh")
    public ResponseEntity<AuthResponse> accessToken(@CookieValue(name = "refresh_token") String accessToken,
                                                    HttpServletRequest request) {

        AuthResponse response = new AuthResponse("qwf");

        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/logout")
    public ResponseEntity<Void> logout(@CookieValue(name = "refresh_token", required = false) String refreshToken,
                                       HttpServletResponse response) {

        if (!refreshToken.isEmpty()) {
            service.revokeRefreshToken(refreshToken);
        }

        ResponseCookie cookie = ResponseCookie.from("refresh_token", "")
                .httpOnly(true)
                .secure(true)
                .path("/api/v1/auth/refresh")
                .maxAge(0)
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return ResponseEntity.noContent().build();
    }

}
