package com.education.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class StudentCourseDTO {
    private Integer studentId;
    private Integer courseId;
    private Double mark;
    private LocalDateTime createdDate;
}
