package com.example.school_management.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;
    @Column(name = "courses")
    private String courses;

    @ManyToOne
    private School school;
    @ManyToOne
    private Student student;
    @ManyToOne
    private Tutor tutor;
}
