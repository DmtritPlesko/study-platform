package com.study.interaction.dto.auth;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterRequest {

    @NotNull(message = "Emil не может быть пустым")
    String email;

    @NotNull(message = "Пароль не предоставлен")
    String password;


    String role;

    @NotNull(message = "Придумайте юзернейм")
    String username;

    String nameGroup;
}
