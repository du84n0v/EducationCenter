package com.education.dto;

import com.education.enums.Gender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StudentFullInfDTO {
    private Integer id;
    private String name;
    private String surname;
    private Integer level;
    private Integer age;
    private Gender gender;
    private LocalDate from;
    private LocalDate to;
}
