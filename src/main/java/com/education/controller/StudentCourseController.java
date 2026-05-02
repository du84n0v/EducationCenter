package com.education.controller;

import com.education.dto.StudentCourseDTO;
import com.education.dto.StudentCourseDetailed;
import com.education.dto.StudentMarkDto;
import com.education.service.StudentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("student-course")
public class StudentCourseController {

    @Autowired
    private StudentCourseService service;

    @PostMapping("/create")
    public ResponseEntity<StudentCourseDTO> create(@RequestBody StudentCourseDTO dto){
        return ResponseEntity.ok(service.createStudentCourse(dto));
    }

    @PutMapping("/by-id/{id}")
    public ResponseEntity<Boolean> update(@PathVariable Integer id,
                                          @RequestBody StudentCourseDTO dto){
        return ResponseEntity.ok(service.updateById(id, dto));
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<StudentCourseDTO> byId(@PathVariable Integer id){
        return ResponseEntity.ok(service.getStudentCourseById(id));
    }

    @GetMapping("/detail-by-id/{id}")
    public ResponseEntity<StudentCourseDetailed> detailInfo(@PathVariable Integer id){
        return ResponseEntity.ok(service.getDetailInfo(id));
    }

    @DeleteMapping("by-id/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id){
        return ResponseEntity.ok(service.deleteStudentCourseById(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<StudentCourseDTO>> list(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/student-mark-on-date/{studentId}/{date}")
    public ResponseEntity<List<StudentMarkDto>> studentMarkOnDate(@PathVariable Integer studentId,
                                                                  @PathVariable LocalDate date){
        return ResponseEntity.ok(service.getStudentMarkOnDate(studentId, date));
    }

    @GetMapping("/student-mark-between/{studentId}/{start}/{end}")
    public ResponseEntity<List<StudentMarkDto>> studentMarkOnDate(@PathVariable Integer studentId,
                                                                  @PathVariable LocalDateTime start,
                                                                  @PathVariable LocalDateTime end){
        return ResponseEntity.ok(service.getStudentMarkBetweenDate(studentId, start, end));
    }

    @GetMapping("/student-mark-desc/{studentId}")
    public ResponseEntity<List<StudentMarkDto>> studentMark(@PathVariable Integer studentId){
        return ResponseEntity.ok(service.getStudentMarkDesc(studentId));
    }

    @GetMapping("/student-mark-on-course/{studentId}/{courseId}")
    public ResponseEntity<List<StudentMarkDto>> studentMarkOnCourse(@PathVariable Integer studentId,
                                                                    @PathVariable Integer courseId){
        return ResponseEntity.ok(service.getStudentMarkOnCourse(studentId, courseId));
    }
}
