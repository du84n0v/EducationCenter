package com.education.service;

import com.education.dto.CourseDTO;
import com.education.entity.Course;
import com.education.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public CourseDTO createCourse(CourseDTO dto) {
        Course course = new Course();
        course.setName(dto.getName());
        course.setPrice(dto.getPrice());
        course.setDuration(dto.getDuration());
        course.setCreatedDate(LocalDateTime.now());
        courseRepository.save(course);
        return dto;
    }

    public CourseDTO getCourseById(Integer id) {
        Optional<Course> optional = courseRepository.findById(id);
        return optional.map(this::courseToDTO).orElse(null);
    }

    private CourseDTO courseToDTO(Course course){
        return new CourseDTO(course.getName(),
                course.getPrice(),
                course.getDuration());
    }

    public List<CourseDTO> getAllCourse() {
        Iterable<Course> optional = courseRepository.findAll();
        List<CourseDTO> result = new LinkedList<>();
        for (Course course : optional) {
            result.add(courseToDTO(course));
        }
        return result;
    }

    public Boolean updateCourseById(Integer id, CourseDTO dto) {
        Optional<Course> optional = courseRepository.findById(id);
        if(optional.isEmpty()){
            return false;
        }
        Course course = optional.get();
        course.setName(dto.getName());
        course.setPrice(dto.getPrice());
        course.setDuration(dto.getDuration());
        courseRepository.save(course);
        return true;
    }

    public Boolean deleteCourseById(Integer id) {
        Optional<Course> optional = courseRepository.findById(id);
        if(optional.isEmpty()){
            return false;
        }
        courseRepository.deleteById(id);
        return true;
    }

    public List<CourseDTO> getCourseByName(String name) {
        List<CourseDTO> result = new LinkedList<>();
        for (Course course : courseRepository.findByNameContaining(name)) {
            result.add(courseToDTO(course));
        }
        return result;
    }

    public List<CourseDTO> getCourseByPrice(Double price) {
        List<CourseDTO> result = new LinkedList<>();
        for (Course course : courseRepository.findByPrice(price)) {
            result.add(courseToDTO(course));
        }
        return result;
    }

    public List<CourseDTO> getCourseByDuration(Integer duration) {
        List<CourseDTO> result = new LinkedList<>();
        for (Course course : courseRepository.findByDuration((duration))) {
            result.add(courseToDTO(course));
        }
        return result;
    }

    public List<CourseDTO> getCourseBetweenPrice(Double begin, Double end) {
        List<CourseDTO> result = new LinkedList<>();
        for (Course course : courseRepository.findByPriceBetween(begin, end)) {
            result.add(courseToDTO(course));
        }
        return result;
    }

    public List<CourseDTO> getCourseBetweenDates(LocalDateTime start, LocalDateTime finish) {
        List<CourseDTO> result = new LinkedList<>();
        for (Course course : courseRepository.findByCreatedDateBetween(start, finish)) {
            result.add(courseToDTO(course));
        }
        return result;
    }
}
