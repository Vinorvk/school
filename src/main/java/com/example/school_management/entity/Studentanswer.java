package com.example.school_management.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "studentanswer")
public class Studentanswer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;
    @Column(name = "student_answer")
    private String student_answer;
    @ManyToOne
    private Student student;
    @ManyToOne
    private Questions question;
}