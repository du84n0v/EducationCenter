package com.education.repository;

import com.education.dto.StudentCourseFullInfoDTO;
import com.education.entity.StudentCourse;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface StudentCourseRepository extends CrudRepository<StudentCourse, Integer> {

    List<StudentCourse> findByStudentIdAndCreatedDateBetween(Integer studentId, LocalDateTime start, LocalDateTime end);

    List<StudentCourse> findByStudentIdOrderByCreatedDateDesc(Integer studentId);

    List<StudentCourse> findByStudentIdAndCourseIdOrderByCreatedDateDesc(Integer studentId, Integer courseId);

    List<StudentCourse> findFirstByStudentIdOrderByCreatedDateDesc(Integer studentId);

    List<StudentCourse> findByStudentIdOrderByMarkDesc(Integer studentId);

    List<StudentCourse> findFirstByStudentIdOrderByCreatedDate(Integer studentId);

    List<StudentCourse> findFirstByStudentIdAndCourseIdOrderByMark(Integer studentId, Integer courseId);

    List<StudentCourse> findByStudentId(Integer studentId);

    List<StudentCourse> findByStudentIdAndCourseId(Integer studentId, Integer courseId);

    Integer countByStudentIdAndMarkGreaterThan(Integer studentId, Double targetMark);

    StudentCourse findFirstByCourseIdOrderByMarkDesc(Integer courseId);

    List<StudentCourse> findByCourseId(Integer courseId);

    Long countByCourseId(Integer courseId);
}
