package com.education.controller;

import com.education.dto.StudentDTO;
import com.education.enums.Gender;
import com.education.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/create")
    public ResponseEntity<StudentDTO> create(@RequestBody StudentDTO dto){
        return ResponseEntity.ok(studentService.createStudent(dto));
    }

    @GetMapping("/list")
    public ResponseEntity<List<StudentDTO>> studentList(){
        return ResponseEntity.ok(studentService.getAllStudent());
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<StudentDTO> byId(@PathVariable Integer id){
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> update(@PathVariable Integer id,
                                          @RequestBody StudentDTO dto){
        return ResponseEntity.ok(studentService.updateStudentById(id, dto));
    }

    @DeleteMapping("/by-id/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id){
        return ResponseEntity.ok(studentService.deleteStudentById(id));
    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<List<StudentDTO>> getAllByName(@PathVariable String name){
        return ResponseEntity.ok(studentService.getAllByName(name));
    }

    @GetMapping("/by-surname/{surname}")
    public ResponseEntity<List<StudentDTO>> getAllBySurname(@PathVariable String surname){
        return ResponseEntity.ok(studentService.getAllBySurname(surname));
    }

    @GetMapping("/by-level/{level}")
    public ResponseEntity<List<StudentDTO>> getAllByLevel(@PathVariable Integer level){
        return ResponseEntity.ok(studentService.getAllByLevel(level));
    }

    @GetMapping("/by-age/{age}")
    public ResponseEntity<List<StudentDTO>> getAllByAge(@PathVariable Integer age){
        return ResponseEntity.ok(studentService.getAllByAge(age));
    }

    @GetMapping("/by-gender/{gender}")
    public ResponseEntity<List<StudentDTO>> getAllByGender(@PathVariable Gender gender){
        return ResponseEntity.ok(studentService.getAllByGender(gender));
    }

    @GetMapping("/by-date/{date}")
    public ResponseEntity<List<StudentDTO>> getAllByGender(@PathVariable LocalDateTime date){
        return ResponseEntity.ok(studentService.getAllByDate(date));
    }

    @GetMapping("/by-between-date/{start}/{end}")
    public ResponseEntity<List<StudentDTO>> getAllByGender(@PathVariable LocalDateTime start,
                                                           @PathVariable LocalDateTime end){
        return ResponseEntity.ok(studentService.getAllByBetweenDate(start, end));
    }

}
