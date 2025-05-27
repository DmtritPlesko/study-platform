package com.study.platform.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Entity
@Table(name = "student_in_course")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentInCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "user_id")
    String stud_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "course_id")
    Course course;
    
}