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
        summary = "Создание нового курса",
        description = """
                Создает новый курс в системе для дальнейшего использования пользователями.
                Курс становится доступен для назначения студентам после успешного создания.
                Доступно только пользователям с ролью ADMIN или TEACHER.
                """,
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Данные нового курса",
                content = @Content(
                        schema = @Schema(implementation = CourseDto.class),
                        mediaType = MediaType.APPLICATION_JSON_VALUE
                )
        )
)
@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Курс успешно создан",
                content = @Content(
                        schema = @Schema(implementation = CourseDto.class),
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                value = """
                                        {
                                          "id": 1,
                                          "title": "Java Programming",
                                          "description": "Основы программирования на Java",
                                          "createdAt": "2024-01-15T10:30:00Z",
                                          "status": "ACTIVE"
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
                description = "Нет прав доступа",
                content = @Content(
                        schema = @Schema(implementation = Error.class),
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                value = """
                                        {
                                          "timestamp": "2025-11-17T07:11:30.936+00:00",
                                          "status": 403,
                                          "error": "Forbidden",
                                          "message": "Недостаточно прав для создания курса"
                                        }
                                        """
                        )
                )
        ),
        @ApiResponse(
                responseCode = "400",
                description = "Некорректные данные курса",
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
                description = "Курс с таким названием уже существует",
                content = @Content(
                        schema = @Schema(implementation = Error.class),
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                value = """
                                        {
                                          "timestamp": "2025-11-17T07:11:30.936+00:00",
                                          "status": 409,
                                          "error": "CourseAlreadyExists",
                                          "message": "Курс с названием 'Java Programming' уже существует"
                                        }
                                        """
                        )
                )
        )
})
public @interface CreateCourseDock {
}
