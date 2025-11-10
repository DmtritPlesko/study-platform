package com.study.platform.controller;

import com.study.interaction.dto.course.CourseDto;
import com.study.platform.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/course")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Tag(name = "Courses", description = "Управление курсами")
public class CourseController {

    final CourseService courseService;

    @Operation(
            summary = "Создание нового курса",
            description = "Создаётся новый курс которым потом могут пользоваться люди"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешное добавление курса"),
            @ApiResponse(responseCode = "401",description = "Пользователь не авторизован"),
            @ApiResponse(responseCode = "403",description = "Нет прав доступа")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CourseDto> createCourse(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Данные для создания курса",
                    required = true
            )
            @RequestBody @Valid CourseDto courseDto) {


        return ResponseEntity.ok(courseService.createCourse(courseDto));
    }

    @Operation(
            summary = "Обновление текущего курса по его айди",
            description = "Обновляем уже созданный курс по его айди,который есть в системе"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешное обновление курса"),
            @ApiResponse(responseCode = "401",description = "Пользователь не авторизован"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав на обновление"),
            @ApiResponse(responseCode = "404", description = "Курс не найден в системе")
    })
    @PutMapping(path = "/{id}")
    public ResponseEntity<CourseDto> updateCourse(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Данные для обновления курса",
                    required = false
            )
            @PathVariable("id") String courseId,
            @RequestBody @Valid CourseDto courseDto) {

        return ResponseEntity.ok(courseService.updateCourse(courseId, courseDto));
    }

    @PatchMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> patchUpdateCourse(@RequestBody CourseDto courseDto) {

        return ResponseEntity.ok().body(courseService.patchUpdateCourse(courseDto));
    }

    @Operation(
            summary = "Удаление курса по его айди",
            description = "ВВодим айди и удаляем курс"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "Успешное удаление курса"),
            @ApiResponse(responseCode = "401",description = "Пользователь не авторизован"),
            @ApiResponse(responseCode ="403" ,description = "Недостаточно прав"),
            @ApiResponse(responseCode = "404",description ="Курс не найден")
    })
    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCourse(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Данные для удаления курса",
                    required = true
            )
            @PathVariable("id") String courseId) {

        courseService.deleteCourse(courseId);
    }

    @Operation(
            summary = "Запись студенита на курса",
            description ="Пользователь записывается на курс")
    @ApiResponses({
            @ApiResponse(responseCode ="200",description = "Успешная запись на курс"),
            @ApiResponse(responseCode ="401",description ="Пользователь не авторизован"),
            @ApiResponse(responseCode = "404",description ="Курс не найден"),
            @ApiResponse(responseCode = "409",description = "Пользователь уже есть в курсе")
    })
    @PostMapping(path = "/{id}/register/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> registerInCourse(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Данные для записи на курса",
                    required = true
            )
            @PathVariable("id") String courseId,
            @PathVariable("userId") String useId) {

        courseService.registerInCourse(courseId, useId);

        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Просмотр всех курсов",
            description = "Просмотреть все крусы которые есть в системе"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "Успешный просмотр курсов"),
            @ApiResponse(responseCode = "401",description = "Пользователь не авторизован"),
    })
    @GetMapping(path = "/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllCourses() {

        return ResponseEntity.ok(courseService.getAllCourses());
    }
}
