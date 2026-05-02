package com.education.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

@Entity
public class Course {//id,name,price,duration,createdDate (LocalDateTime-da)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column
    private Double price;
    @Column
    private Integer duration;
    @Column(name = "date")
    private LocalDateTime createdDate;

}
