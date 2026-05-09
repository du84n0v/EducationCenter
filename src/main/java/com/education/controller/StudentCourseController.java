package com.education.controller;

import com.education.dto.StudentCourseDTO;
import com.education.dto.StudentCourseDetailed;
import com.education.dto.StudentCourseFullInfoDTO;
import com.education.dto.StudentMarkDto;
import com.education.service.StudentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
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

    @GetMapping("/student-last-mark/{studentId}")
    public ResponseEntity<StudentMarkDto> lastMark(@PathVariable Integer studentId){
        return ResponseEntity.ok(service.getStudentLastMark(studentId));
    }

    @GetMapping("/top-3-mark/{studentId}")
    public ResponseEntity<List<StudentMarkDto>> top3Mark(@PathVariable Integer studentId){
        return ResponseEntity.ok(service.getStudentTop3Mark(studentId));
    }

    @GetMapping("/student-first-mark/{studentId}")
    public ResponseEntity<StudentMarkDto> firstMark(@PathVariable Integer studentId){
        return ResponseEntity.ok(service.getStudentFirstMark(studentId));
    }

    @GetMapping("/first-course-mark/{studentId}")
    public ResponseEntity<StudentMarkDto> firstCourseFirstMark(@PathVariable Integer studentId){
        return ResponseEntity.ok(service.getFirstCourseFirstMark(studentId));
    }

    @GetMapping("/top-mark-on-course/{studentId}/{courseId}")
    public ResponseEntity<StudentMarkDto> topMarkOnCourse(@PathVariable Integer studentId,
                                                          @PathVariable Integer courseId){
        return ResponseEntity.ok(service.getStudentTopMarkOnCourse(studentId, courseId));
    }

    @GetMapping("/avg-mark/{studentId}")
    public ResponseEntity<Double> avgMark(@PathVariable Integer studentId){
        return ResponseEntity.ok(service.getStudentAvgMark(studentId));
    }

    @GetMapping("/avg-student-mark-on-course/{studentId}/{courseId}")
    public ResponseEntity<Double> avgStudentMarkOnCourse(@PathVariable Integer studentId,
                                                          @PathVariable Integer courseId){
        return ResponseEntity.ok(service.getStudentAvgMarkOnCourse(studentId, courseId));
    }

    @GetMapping("/count-of-mark-greater-then/{studentId}/{targetMark}")
    public ResponseEntity<Integer> countMarkGreater(@PathVariable Integer studentId,
                                                    @PathVariable Double targetMark){
        return ResponseEntity.ok(service.getMarkCountGreaterThenTarget(studentId, targetMark));
    }

    @GetMapping("/top-mark_on-course/{courseId}")
    public ResponseEntity<Double> topMarkOnCourse(@PathVariable Integer courseId){
        return ResponseEntity.ok(service.getTopMarkOnCourse(courseId));
    }

    @GetMapping("/avg-mark-on-course/{courseId}")
    public ResponseEntity<Double> avgMarkOnCourse(@PathVariable Integer courseId){
        return ResponseEntity.ok(service.getAvgMarkOnCourse(courseId));
    }

    @GetMapping("/count-of-mark-on-course/{courseId}")
    public ResponseEntity<Long> markCountOnCourse(@PathVariable Integer courseId){
        return ResponseEntity.ok(service.getMarkCountOnCourse(courseId));
    }

    @PostMapping("/filter")
    public ResponseEntity<PageImpl<StudentCourseDTO>> filter(@RequestParam(name = "page", defaultValue = "1") Integer page,
                                                                     @RequestParam(name = "size", defaultValue = "10") Integer size,
                                                                     @RequestBody StudentCourseFullInfoDTO dto){
        return ResponseEntity.ok(service.studentCourseFilter(dto, page-1, size));
    }
}
