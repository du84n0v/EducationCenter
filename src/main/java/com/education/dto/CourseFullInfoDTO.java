package com.education.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CourseFullInfoDTO {
    private Integer id;
    private String name;
    private Double price;
    private Integer duration;
    private LocalDate from;
    private LocalDate to;
}
