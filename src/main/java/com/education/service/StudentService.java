package com.education.service;

import com.education.dto.StudentDTO;
import com.education.entity.Student;
import com.education.enums.Gender;
import com.education.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<StudentDTO> getAllStudent() {
        Iterable<Student> students = studentRepository.findAll();
        List<StudentDTO> response = new LinkedList<>();
        for(Student student :students){
            response.add(studentToDTO(student));
        }
        return response;
    }

    public StudentDTO createStudent(StudentDTO dto) {
        Student student = new Student();
        student.setName(dto.getName());
        student.setSurname(dto.getSurname());
        student.setAge(dto.getAge());
        student.setLevel(dto.getLevel());
        student.setGender(dto.getGender());
        student.setCreatedDate(LocalDateTime.now());
        studentRepository.save(student);
        return dto;
    }

    public StudentDTO getStudentById(Integer studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        return student.map(value -> new StudentDTO(value.getName(),
                value.getSurname(),
                value.getLevel(),
                value.getAge(),
                value.getGender()))
                .orElse(null);
    }

    public Boolean updateStudentById(Integer studentId, StudentDTO dto) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if(optionalStudent.isEmpty()){
            return false;
        }
        Student student = optionalStudent.get();
        student.setName(dto.getName());
        student.setSurname(dto.getSurname());
        student.setLevel(dto.getLevel());
        student.setAge(dto.getAge());
        studentRepository.save(student);
        return true;
    }

    public Boolean deleteStudentById(Integer id) {
        Optional<Student> optional = studentRepository.findById(id);
        if(optional.isEmpty()){
            return false;
        }
        studentRepository.deleteById(id);
        return true;
    }

    public List<StudentDTO> getAllByName(String name) {
        List<Student> list = studentRepository.getAllByName(name);

        List<StudentDTO> result = new LinkedList<>();
        for(Student student :list){
            result.add(studentToDTO(student));
        }
        return result;
    }

    public List<StudentDTO> getAllBySurname(String surname) {
        List<Student> list = studentRepository.getAllBySurname(surname);

        List<StudentDTO> result = new LinkedList<>();
        for(Student student :list){
            result.add(studentToDTO(student));
        }
        return result;
    }

    private StudentDTO studentToDTO(Student student) {
        return new StudentDTO(student.getName(),
                student.getSurname(),
                student.getLevel(),
                student.getAge(),
                student.getGender());
    }

    public List<StudentDTO> getAllByLevel(Integer level) {
        List<StudentDTO> result = new LinkedList<>();
        for(Student student :studentRepository.getAllByLevel(level)){
            result.add(studentToDTO(student));
        }
        return result;
    }

    public List<StudentDTO> getAllByAge(Integer age) {
        List<StudentDTO> result = new LinkedList<>();
        for(Student student :studentRepository.getAllByAge(age)){
            result.add(studentToDTO(student));
        }
        return result;
    }

    public List<StudentDTO> getAllByGender(Gender gender) {
        List<StudentDTO> result = new LinkedList<>();
        for(Student student :studentRepository.getAllByGender(gender)){
            result.add(studentToDTO(student));
        }
        return result;
    }

    public List<StudentDTO> getAllByDate(LocalDateTime date) {
        List<StudentDTO> result = new LinkedList<>();
        for(Student student :studentRepository.getAllByCreatedDate(date)){
            result.add(studentToDTO(student));
        }
        return result;
    }


    public List<StudentDTO> getAllByBetweenDate(LocalDateTime start, LocalDateTime end) {
        List<StudentDTO> result = new LinkedList<>();
        for(Student student :studentRepository.getAllByCreatedDateBetween(start, end)){
            result.add(studentToDTO(student));
        }
        return result;
    }
}
