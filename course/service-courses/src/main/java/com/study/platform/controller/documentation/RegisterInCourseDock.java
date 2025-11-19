package com.study.platform.controller.documentation;

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
        summary = "Запись студента на курс",
        description = """
        Регистрирует пользователя на указанный курс.
        После успешной записи студент получает доступ к материалам курса
        и появляется в списке участников курса.
        """
)
@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Успешная запись на курс",
                content = @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                value = """
                    {
                      "message": "Студент успешно записан на курс",
                      "courseId": "12345",
                      "userId": "67890",
                      "registeredAt": "2024-01-15T10:30:00Z"
                    }
                    """
                        )
                )
        ),
        @ApiResponse(
                responseCode = "401",
                description = "Пользователь не авторизован",
                content = @Content(
                        schema = @Schema(implementation = Error.class),
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                value = """
                    {
                      "timestamp": "2025-11-17T07:11:30.936+00:00",
                      "status": 401,
                      "error": "Unauthorized",
                      "message": "Требуется аутентификация"
                    }
                    """
                        )
                )
        ),
        @ApiResponse(
                responseCode = "404",
                description = "Курс или пользователь не найден",
                content = @Content(
                        schema = @Schema(implementation = Error.class),
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                value = """
                    {
                      "timestamp": "2025-11-17T07:11:30.936+00:00",
                      "status": 404,
                      "error": "NotFound",
                      "message": "Курс с ID '12345' не найден"
                    }
                    """
                        )
                )
        ),
        @ApiResponse(
                responseCode = "409",
                description = "Пользователь уже записан на курс",
                content = @Content(
                        schema = @Schema(implementation = Error.class),
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                value = """
                    {
                      "timestamp": "2025-11-17T07:11:30.936+00:00",
                      "status": 409,
                      "error": "AlreadyRegistered",
                      "message": "Пользователь уже записан на этот курс"
                    }
                    """
                        )
                )
        ),
        @ApiResponse(
                responseCode = "400",
                description = "Некорректные данные",
                content = @Content(
                        schema = @Schema(implementation = Error.class),
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                value = """
                    {
                      "timestamp": "2025-11-17T07:11:30.936+00:00",
                      "status": 400,
                      "error": "BadRequest",
                      "message": "Некорректный формат ID курса или пользователя"
                    }
                    """
                        )
                )
        )
})
public @interface RegisterInCourseDock {
}
