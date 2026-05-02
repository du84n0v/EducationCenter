package com.education.repository;

import com.education.entity.Student;
import com.education.enums.Gender;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface StudentRepository extends CrudRepository<Student, Integer> {

    List<Student> getAllByName(String name);

    List<Student> getAllBySurname(String surname);

    List<Student> getAllByLevel(Integer level);

    List<Student> getAllByGender(Gender gender);

    List<Student> getAllByAge(Integer age);

    List<Student> getAllByCreatedDate(LocalDateTime date);

    List<Student> getAllByCreatedDateBetween(LocalDateTime start, LocalDateTime end);
}
