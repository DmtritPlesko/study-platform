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
        summary = "Удаление курса по его айди",
        description = """
        Удаляет курс из системы по указанному идентификатору.
        Операция необратима - все данные курса будут безвозвратно удалены.
        Доступно только для пользователей с правами администратора.
        """
)
@ApiResponses({
        @ApiResponse(
                responseCode = "204",
                description = "Курс успешно удален"
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
                description = "Недостаточно прав",
                content = @Content(
                        schema = @Schema(implementation = Error.class),
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                value = """
                    {
                      "timestamp": "2025-11-17T07:11:30.936+00:00",
                      "status": 403,
                      "error": "Forbidden",
                      "message": "Недостаточно прав для удаления курса"
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
        ),
        @ApiResponse(
                responseCode = "409",
                description = "Невозможно удалить курс",
                content = @Content(
                        schema = @Schema(implementation = Error.class),
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                value = """
                    {
                      "timestamp": "2025-11-17T07:11:30.936+00:00",
                      "status": 409,
                      "error": "Conflict",
                      "message": "Невозможно удалить курс: имеются активные студенты"
                    }
                    """
                        )
                )
        )
})
public @interface DeleteCourseDock {
}
