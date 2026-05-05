package com.education.service;

import com.education.dto.*;
import com.education.entity.StudentCourse;
import com.education.repository.StudentCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentCourseService {

    @Autowired
    private StudentCourseRepository repository;

    public StudentCourseDTO createStudentCourse(StudentCourseDTO dto) {
        StudentCourse studentCourse = new StudentCourse();
        studentCourse.setStudentId(dto.getStudentId());
        studentCourse.setCourseId(dto.getCourseId());
        studentCourse.setMark(dto.getMark());
        studentCourse.setCreatedDate(LocalDateTime.now());
        dto.setCreatedDate(studentCourse.getCreatedDate());
        repository.save(studentCourse);
        return dto;
    }

    public Boolean updateById(Integer id, StudentCourseDTO dto) {
        Optional<StudentCourse> optional = repository.findById(id);
        if(optional.isEmpty()){
            return false;
        }
        StudentCourse studentCourse = new StudentCourse();
        studentCourse.setStudentId(dto.getStudentId());
        studentCourse.setCourseId(dto.getCourseId());
        studentCourse.setMark(dto.getMark());
        repository.save(studentCourse);
        return true;

    }

    public StudentCourseDTO getStudentCourseById(Integer id) {
        Optional<StudentCourse> optional = repository.findById(id);
        return (optional.map(this::entityToDTO).orElse(null));
    }

    public StudentCourseDTO entityToDTO(StudentCourse studentCourse){
        return new StudentCourseDTO(studentCourse.getStudentId(),
                studentCourse.getCourseId(),
                studentCourse.getMark(),
                studentCourse.getCreatedDate());
    }

    public StudentCourseDetailed getDetailInfo(Integer id) {
        Optional<StudentCourse> optional = repository.findById(id);
        if(optional.isEmpty()){
            return null;
        }
        StudentCourse st = optional.get();
        StudentCourseDetailed result = new StudentCourseDetailed();
        result.setId(st.getId());
        result.setMark(st.getMark());
        result.setCreatedDate(st.getCreatedDate());
        result.setStudentShort(new StudentShortInfo(st.getStudentId(),
                st.getStudent().getName(),
                st.getStudent().getSurname()));
        result.setCourseShort(new CourseShortInfo(st.getCourseId(),
                st.getCourse().getName()));
        return result;
    }

    public Boolean deleteStudentCourseById(Integer id) {
        Optional<StudentCourse> optional = repository.findById(id);
        if(optional.isEmpty()){
            return false;
        }
        repository.deleteById(id);
        return true;
    }

    public List<StudentCourseDTO> getAll() {
        Iterable<StudentCourse> optional = repository.findAll();
        List<StudentCourseDTO> result = new LinkedList<>();
        for (StudentCourse studentCourse : optional) {
            result.add(entityToDTO(studentCourse));
        }
        return result;
    }

    public List<StudentMarkDto> getStudentMarkOnDate(Integer studentId, LocalDate date) {
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.atTime(LocalTime.MAX);
        return entityToStudentMark(repository.findByStudentIdAndCreatedDateBetween(studentId, start, end));
    }

    public List<StudentMarkDto> getStudentMarkBetweenDate(Integer studentId, LocalDateTime start, LocalDateTime end) {
        return entityToStudentMark(repository.findByStudentIdAndCreatedDateBetween(studentId, start, end));
    }

    public List<StudentMarkDto> entityToStudentMark(List<StudentCourse> list){
        List<StudentMarkDto> result = new LinkedList<>();
        for (StudentCourse ls : list) {
            result.add(new StudentMarkDto(ls.getCourse().getName(), ls.getMark(), ls.getCreatedDate()));
        }
        return result;
    }

    public List<StudentMarkDto> getStudentMarkDesc(Integer studentId) {
        return entityToStudentMark(repository.findByStudentIdOrderByCreatedDateDesc(studentId));
    }

    public List<StudentMarkDto> getStudentMarkOnCourse(Integer studentId, Integer courseId) {
        return entityToStudentMark(repository.findByStudentIdAndCourseIdOrderByCreatedDateDesc(studentId, courseId));
    }

    public StudentMarkDto getStudentLastMark(Integer studentId) {
        List<StudentMarkDto> result = entityToStudentMark(repository.findFirstByStudentIdOrderByCreatedDateDesc(studentId));
        return (result.isEmpty() ? null : result.getFirst());
    }

    public List<StudentMarkDto> getStudentTop3Mark(Integer studentId) {
        List<StudentCourse> list = repository.findByStudentIdOrderByMarkDesc(studentId);

        List<StudentCourse> top3 = new LinkedList<>();
        for(int i = 0; i < Math.max(3, list.size()); ++ i){
            top3.add(list.get(i));
        }
        return entityToStudentMark(top3);
    }

    public StudentMarkDto getStudentFirstMark(Integer studentId) {
        List<StudentMarkDto> result = entityToStudentMark(repository.findFirstByStudentIdOrderByCreatedDate(studentId));
        return (result.isEmpty() ? null : result.getFirst());
    }

    public StudentMarkDto getFirstCourseFirstMark(Integer studentId) {
        List<StudentMarkDto> response = entityToStudentMark(repository.findFirstByStudentIdOrderByCreatedDate(studentId));
        return (response.isEmpty() ? null : response.getFirst());
    }

    public StudentMarkDto getStudentTopMarkOnCourse(Integer studentId, Integer courseId) {
        List<StudentMarkDto> response = entityToStudentMark(repository.findFirstByStudentIdAndCourseIdOrderByMark(studentId, courseId));
        return (response.isEmpty() ? null : response.getFirst());
    }

    public Double getStudentAvgMark(Integer studentId) {
        List<StudentCourse> response = repository.findByStudentId(studentId);
        double avg = 0;
        for (StudentCourse student : response) {
            avg += 1D*student.getMark();
        }
        return avg / response.size();
    }

    public Double getStudentAvgMarkOnCourse(Integer studentId, Integer courseId) {
        List<StudentCourse> response = repository.findByStudentIdAndCourseId(studentId, courseId);
        double avg = 0D;
        for (StudentCourse student : response) {
            avg += 1D*student.getMark();
        }
        return avg / response.size();
    }

    public Integer getMarkCountGreaterThenTarget(Integer studentId, Double targetMark) {
        return repository.countByStudentIdAndMarkGreaterThan(studentId, targetMark);
    }

    public Double getTopMarkOnCourse(Integer courseId) {
        StudentCourse sc = repository.findFirstByCourseIdOrderByMarkDesc(courseId);
        return (sc == null ? null : sc.getMark());
    }

    public Double getAvgMarkOnCourse(Integer courseId) {
        List<StudentCourse> response = repository.findByCourseId(courseId);
        double avg = 0D;
        for (StudentCourse studentCourse : response) {
            avg += 1D*studentCourse.getMark();
        }
        return avg / response.size();
    }

    public Long getMarkCountOnCourse(Integer courseId) {
        return repository.countByCourseId(courseId);
    }
}
