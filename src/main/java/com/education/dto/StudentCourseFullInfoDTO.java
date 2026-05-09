package com.education.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StudentCourseFullInfoDTO {
    private Integer studentId;
    private Integer courseId;
    private Integer markFrom;
    private Integer markTo;
    private String studentName;
    private String courseName;
    private LocalDate dateFrom;
    private LocalDate dateTo;
}
