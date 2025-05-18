package com.study.interaction.dto.course;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseDto {

    String id;

    String name;

    String title;

    String description;

    String university;

}
