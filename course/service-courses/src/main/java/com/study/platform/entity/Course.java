package com.study.platform.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "courses")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "title")
    String title;

    @Column(name = "description")
    String description;

    @Column(name = "university")
    String university;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    List<StudentInCourse> studentInCourses = new ArrayList<>();

    @Transient
    List<String> students;

    public List<String> getStudents() {

        if (students.isEmpty()) {
            students = studentInCourses.stream()
                    .map(StudentInCourse::getStud_id)
                    .collect(Collectors.toList());
        }

        return students;
    }

}
