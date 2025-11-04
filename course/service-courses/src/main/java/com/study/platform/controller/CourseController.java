package com.study.platform.controller;

import com.study.interaction.dto.course.CourseDto;
import com.study.platform.service.CourseService;
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
public class CourseController {

    final CourseService courseService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CourseDto> createCourse(@RequestBody CourseDto courseDto) {
        return ResponseEntity.ok(courseService.createCourse(courseDto));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<CourseDto> updateCourse(@PathVariable("id") String courseId,
                                                  @RequestBody CourseDto courseDto) {
        return ResponseEntity.ok(courseService.updateCourse(courseId, courseDto));
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCourse(@PathVariable("id") String courseId) {
        courseService.deleteCourse(courseId);
    }

    @PostMapping(path = "/{id}/register/{userId}")
    public ResponseEntity<Void> registerInCourse(@PathVariable("id") String courseId,
                                                 @PathVariable("userId") String useId) {
        courseService.registerInCourse(courseId, useId);

        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllCourses() {

        return ResponseEntity.ok(courseService.getAllCourses());
    }
}
