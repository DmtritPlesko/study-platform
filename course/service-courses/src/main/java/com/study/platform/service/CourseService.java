package com.study.platform.service;

import com.study.interaction.dto.course.CourseDto;

public interface CourseService {

    CourseDto createCourse(CourseDto courseDto);

    CourseDto updateCourse(String courseId,CourseDto courseDto);

    void deleteCourse(String courseId);

    void registerInCourse(String courseId,String userId);
}
