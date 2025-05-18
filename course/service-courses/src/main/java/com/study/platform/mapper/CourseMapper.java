package com.study.platform.mapper;

import com.study.interaction.dto.course.CourseDto;
import com.study.platform.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CourseMapper {

    @Mapping(target = "id" ,ignore = true)
    Course toCourse(CourseDto courseDto);

    CourseDto toCourseDto(Course course);

}
