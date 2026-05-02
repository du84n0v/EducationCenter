package com.education.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class StudentCourseDetailed {
    private Integer id;
    private StudentShortInfo studentShort;
    private CourseShortInfo courseShort;
    private Double mark;
    private LocalDateTime createdDate;
}
