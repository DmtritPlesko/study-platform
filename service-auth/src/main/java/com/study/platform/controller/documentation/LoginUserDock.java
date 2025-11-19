package com.study.platform.controller.documentation;

import com.study.interaction.dto.auth.AuthResponse;
import com.study.interaction.dto.auth.LoginRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Operation(
        summary = "Аутентификация пользователя в системе",
        description = """
                Выполняет вход пользователя в систему через HTML форму.
                После успешной аутентификации возвращает JWT токен для доступа к защищенным endpoints.
                Токен должен передаваться в заголовке Authorization для последующих запросов.
                """,
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Учетные данные пользователя",
                content = @Content(
                        schema = @Schema(implementation = LoginRequest.class),
                        mediaType = MediaType.APPLICATION_JSON_VALUE
                )
        )
)
@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Успешная аутентификация",
                content = @Content(
                        schema = @Schema(implementation = AuthResponse.class),
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                value = """
                                        {
                                          "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
                                          "type": "Bearer",
                                          "username": "john_doe",
                                          "email": "john@example.com",
                                          "roles": ["USER"]
                                        }
                                        """
                        )
                )
        ),
        @ApiResponse(
                responseCode = "401",
                description = "Неверные учетные данные",
                content = @Content(
                        schema = @Schema(implementation = Error.class),
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                value = """
                                        {
                                          "timestamp": "2025-11-17T07:11:30.936+00:00",
                                          "status": 401,
                                          "error": "InvalidCredentials",
                                          "message": "Неверный email или пароль"
                                        }
                                        """
                        )
                )
        ),
        @ApiResponse(
                responseCode = "400",
                description = "Некорректный формат запроса",
                content = @Content(
                        schema = @Schema(implementation = Error.class),
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                value = """
                                        {
                                          "timestamp": "2025-11-17T07:11:30.936+00:00",
                                          "status": 400,
                                          "error": "ValidationError",
                                          "message": "Email должен быть валидным адресом"
                                        }
                                        """
                        )
                )
        )
})
public @interface LoginUserDock {
}
