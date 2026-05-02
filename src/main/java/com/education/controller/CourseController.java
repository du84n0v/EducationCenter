package com.education.controller;

import com.education.dto.CourseDTO;
import com.education.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/create")
    public ResponseEntity<CourseDTO> create(@RequestBody CourseDTO dto){
        return ResponseEntity.ok(courseService.createCourse(dto));
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<CourseDTO> byId(@PathVariable Integer id){
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<CourseDTO>> courseList(){
        return ResponseEntity.ok(courseService.getAllCourse());
    }

    @PutMapping("/by-id/{id}")
    public ResponseEntity<Boolean> update(@PathVariable Integer id,
                                          @RequestBody CourseDTO dto){
        return ResponseEntity.ok(courseService.updateCourseById(id, dto));
    }

    @DeleteMapping("/by-id/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id){
        return ResponseEntity.ok(courseService.deleteCourseById(id));
    }

    @GetMapping("by-name/{name}")
    public ResponseEntity<List<CourseDTO>> byName(@PathVariable String name){
        return ResponseEntity.ok(courseService.getCourseByName(name));
    }

    @GetMapping("by-price/{price}")
    public ResponseEntity<List<CourseDTO>> byPrice(@PathVariable Double price){
        return ResponseEntity.ok(courseService.getCourseByPrice(price));
    }

    @GetMapping("by-duration/{duration}")
    public ResponseEntity<List<CourseDTO>> byDuration(@PathVariable Integer duration){
        return ResponseEntity.ok(courseService.getCourseByDuration(duration));
    }

    @GetMapping("/between-price/{begin}/{end}")
    public ResponseEntity<List<CourseDTO>> betweenPrice(@PathVariable Double begin,
                                                        @PathVariable Double end){
        return ResponseEntity.ok(courseService.getCourseBetweenPrice(begin, end));
    }

    @GetMapping("/between-date/{start}/{finish}")
    public ResponseEntity<List<CourseDTO>> betweenDates(@PathVariable LocalDateTime start,
                                                        @PathVariable LocalDateTime finish){
        return ResponseEntity.ok(courseService.getCourseBetweenDates(start, finish));
    }
}
