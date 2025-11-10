package com.study.interaction.dto.course;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseDto {

    String id;

    @NotNull(message = "Название не может быть пустым")
    String name;

    @NotNull(message = "Заголовок не может быть пустым")
    String title;

    @NotNull(message = "Описание не может быть пустым")
    String description;

    @NotNull(message = "Название университета не может быть пустым")
    String university;

}
