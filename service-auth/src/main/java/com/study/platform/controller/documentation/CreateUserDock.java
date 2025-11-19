package com.study.platform.controller.documentation;

import com.study.interaction.dto.auth.RegisterRequest;
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
        summary = "Создание нового клиента и сохранение в базу данных",
        description = """
                Создает нового клиента через модальное окно на HTML форме.
                После успешной операции по созданию, заносит клиента в базу, 
                и предлагает нам выбрать его на форме, для дальнейших действий с ним.
                """,
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Данные нового клиента",
                content = @Content(
                        schema = @Schema(implementation = RegisterRequest.class),
                        mediaType = MediaType.APPLICATION_JSON_VALUE
                )
        )
)
@ApiResponses({
        @ApiResponse(
                responseCode = "201",
                description = "Клиент создан успешно!"
        ),
        @ApiResponse(
                responseCode = "409",
                description = "Пользователь уже существует",
                content = @Content(
                        schema = @Schema(implementation = Error.class),
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                value = """
                                        {
                                          "timestamp": "2025-11-17T07:11:30.936+00:00",
                                          "status": 409,
                                          "error": "VALIDATION_ERROR",
                                          "message": "Email already registered"
                                        }
                                        """
                        )
                )
        )
})
public @interface CreateUserDock {
}
