package com.education.dto;

import com.education.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class StudentDTO {
    private String name;
    private String surname;
    private Integer level;
    private Integer age;
    private Gender gender;
}
