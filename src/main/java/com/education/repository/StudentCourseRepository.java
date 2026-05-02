package com.education.repository;

import com.education.entity.StudentCourse;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface StudentCourseRepository extends CrudRepository<StudentCourse, Integer> {

    List<StudentCourse> findByStudentIdAndCreatedDateBetween(Integer studentId, LocalDateTime start, LocalDateTime end);

    List<StudentCourse> findByStudentIdOrderByCreatedDateDesc(Integer studentId);

    List<StudentCourse> findByStudentIdAndCourseIdOrderByCreatedDateDesc(Integer studentId, Integer courseId);
}
