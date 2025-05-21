package com.example.school_management.dto;

import com.example.school_management.entity.Student;
import com.example.school_management.entity.Tutor;
import lombok.Data;

import java.util.List;

@Data
public class SchoolDTO {
    private String schoolId;
    private List<Student> students;
    private List<Tutor> tutors;

    public SchoolDTO(String schoolId, List<Student> students, List<Tutor> tutors) {
        this.schoolId = schoolId;
        this.students = students;
        this.tutors = tutors;
    }
}