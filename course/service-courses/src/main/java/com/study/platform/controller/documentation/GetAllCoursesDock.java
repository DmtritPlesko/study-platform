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
        summary = "Просмотр всех курсов",
        description = """
        Возвращает список всех курсов, доступных в системе.
        Курсы включают основную информацию: название, описание, длительность и статус.
        Список может быть отфильтрован в зависимости от прав доступа пользователя.
        """
)
@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Успешное получение списка курсов",
                content = @Content(
                        schema = @Schema(implementation = CourseDto.class),
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                value = """
                    {
                      "courses": [
                        {
                          "id": "12345",
                          "title": "Java Programming",
                          "description": "Основы программирования на Java",
                          "durationHours": 60,
                          "status": "ACTIVE",
                          "createdAt": "2024-01-15T10:30:00Z"
                        },
                        {
                          "id": "12346",
                          "title": "Spring Framework",
                          "description": "Современная разработка на Spring",
                          "durationHours": 40,
                          "status": "ACTIVE",
                          "createdAt": "2024-01-16T14:20:00Z"
                        }
                      ],
                      "totalCount": 2,
                      "page": 1,
                      "pageSize": 10
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
                responseCode = "204",
                description = "Курсы не найдены",
                content = @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                value = """
                    {
                      "courses": [],
                      "totalCount": 0,
                      "message": "Курсы не найдены"
                    }
                    """
                        )
                )
        )
})
public @interface GetAllCoursesDock {
}
