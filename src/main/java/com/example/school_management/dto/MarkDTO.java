package com.example.school_management.dto;

import lombok.Data;

@Data
public class MarkDTO {
    private String student_id;
    private String student_name;
    private int marks;

    public MarkDTO(String student_id, String student_name, int marks) {
        this.student_id = student_id;
        this.student_name = student_name;
        this.marks = marks;
    }
}
