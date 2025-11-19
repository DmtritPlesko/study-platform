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
        summary = "Частичное обновление курса",
        description = """
        Выполняет частичное обновление данных курса. 
        Обновляются только переданные поля, остальные остаются без изменений.
        Идентификатор курса передается в теле запроса.
        """,
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Данные для частичного обновления курса",
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
                      "title": "Updated Java Course",
                      "description": "Обновленное описание курса",
                      "durationHours": 60,
                      "updatedAt": "2024-01-15T14:30:00Z"
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
                      "message": "ID курса обязателен для обновления"
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
                description = "Недостаточно прав для обновления",
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
                description = "Курс не найден",
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
        )
})
public @interface PatchUpdateCourseDock {
}
