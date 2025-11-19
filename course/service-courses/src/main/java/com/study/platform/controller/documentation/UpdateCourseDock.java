package com.study.platform.controller.documentation;

import com.study.interaction.dto.course.CourseDto;
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
        summary = "Обновление текущего курса по его айди",
        description = """
        Обновляет данные существующего курса в системе по его идентификатору.
        Позволяет изменить название, описание и другие параметры курса.
        Доступно только для авторизованных пользователей с соответствующими правами.
        """,
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Обновленные данные курса",
                content = @Content(
                        schema = @Schema(implementation = CourseDto.class),
                        mediaType = MediaType.APPLICATION_JSON_VALUE
                )
        )
)
@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Курс успешно обновлен",
                content = @Content(
                        schema = @Schema(implementation = CourseDto.class),
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                value = """
                    {
                      "id": "12345",
                      "title": "Advanced Java Programming",
                      "description": "Продвинутые темы программирования на Java",
                      "durationHours": 80,
                      "updatedAt": "2024-01-15T12:30:00Z"
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
                responseCode = "403",
                description = "Недостаточно прав на обновление",
                content = @Content(
                        schema = @Schema(implementation = Error.class),
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                value = """
                    {
                      "timestamp": "2025-11-17T07:11:30.936+00:00",
                      "status": 403,
                      "error": "Forbidden",
                      "message": "Недостаточно прав для обновления курса"
                    }
                    """
                        )
                )
        ),
        @ApiResponse(
                responseCode = "404",
                description = "Курс не найден в системе",
                content = @Content(
                        schema = @Schema(implementation = Error.class),
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                value = """
                    {
                      "timestamp": "2025-11-17T07:11:30.936+00:00",
                      "status": 404,
                      "error": "CourseNotFound",
                      "message": "Курс с ID '12345' не найден"
                    }
                    """
                        )
                )
        ),
        @ApiResponse(
                responseCode = "400",
                description = "Некорректные данные для обновления",
                content = @Content(
                        schema = @Schema(implementation = Error.class),
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                value = """
                    {
                      "timestamp": "2025-11-17T07:11:30.936+00:00",
                      "status": 400,
                      "error": "ValidationError",
                      "message": "Название курса не может быть пустым"
                    }
                    """
                        )
                )
        ),
        @ApiResponse(
                responseCode = "409",
                description = "Конфликт данных при обновлении",
                content = @Content(
                        schema = @Schema(implementation = Error.class),
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                value = """
                    {
                      "timestamp": "2025-11-17T07:11:30.936+00:00",
                      "status": 409,
                      "error": "CourseAlreadyExists",
                      "message": "Курс с названием 'Advanced Java Programming' уже существует"
                    }
                    """
                        )
                )
        )
})
public @interface UpdateCourseDock {
}
