package com.study.platform.service;

import com.study.interaction.dto.course.CourseDto;
import com.study.interaction.exception.CourseNotFoundException;
import com.study.platform.entity.Course;
import com.study.platform.mapper.CourseMapper;
import com.study.platform.repository.CourseRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseServiceImpl implements CourseService {

    final CourseRepository courseRepository;
    final CourseMapper mapper;

    @Override
    public CourseDto createCourse(CourseDto courseDto) {
        return mapper.toCourseDto(courseRepository.save(mapper.toCourse(courseDto)));
    }

    @Override
    public CourseDto updateCourse(String courseId, CourseDto courseDto) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Курс не найден для обновления"));

        if (courseDto.getDescription() != null) {
            course.setDescription(courseDto.getDescription());
        }
        if (courseDto.getName() != null) {
            course.setName(courseDto.getName());
        }
        if (courseDto.getTitle() != null) {
            course.setTitle(courseDto.getTitle());
        }
        if (courseDto.getUniversity() != null) {
            course.setUniversity(courseDto.getUniversity());
        }

        return mapper.toCourseDto(courseRepository.save(course));
    }

    @Override
    public void deleteCourse(String courseId) {

        if (courseRepository.findById(courseId).isEmpty()) {
            throw new CourseNotFoundException("Курс не найден для удаления");
        }

        courseRepository.deleteById(courseId);

    }

    @Override
    public void registerInCourse(String courseId, String userId) {

    }

    @Override
    public List<CourseDto> getAllCourses() {

        return courseRepository.findAll().stream()
                .map(mapper::toCourseDto)
                .toList();
    }
}
