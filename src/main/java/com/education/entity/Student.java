package com.education.entity;

import com.education.enums.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

//id,name,surname,level,age,Gender,createdDate (LocalDateTime-da)
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private Integer level;
    @Column
    private Integer age;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
}
