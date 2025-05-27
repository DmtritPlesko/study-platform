package com.study.platform.repository;

import com.study.platform.entity.StudentInCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StudentInCourseRepository extends JpaRepository<StudentInCourse,Long> {
    List<StudentInCourse> findByCourseId(Long courseId);
    boolean existsByUserIdAndCourseId(String userId, Long courseId);
    void deleteByUserIdAndCourseId(String userId, Long courseId);
}
