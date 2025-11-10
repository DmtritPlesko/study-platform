package com.study.platform.service;

import com.study.interaction.dto.course.CourseDto;
import java.util.List;

public interface CourseService {

    CourseDto createCourse(CourseDto courseDto);

    CourseDto updateCourse(String courseId,CourseDto courseDto);

    CourseDto patchUpdateCourse(CourseDto courseDto);

    void deleteCourse(String courseId);

    void registerInCourse(String courseId,String userId);

    List<CourseDto> getAllCourses();
}
