package com.education.repository;

import com.education.entity.Course;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CourseRepository extends CrudRepository<Course, Integer> {

    List<Course> findByNameContaining(String name);

    List<Course> findByPrice(Double price);

    List<Course> findByDuration(Integer duration);

    List<Course> findByPriceBetween(Double priceAfter, Double priceBefore);

    List<Course> findByCreatedDateBetween(LocalDateTime createdDateAfter, LocalDateTime createdDateBefore);
}
