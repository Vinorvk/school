package com.example.school_management.controller;

import com.example.school_management.dto.ResponseDTO;
import com.example.school_management.entity.Student;
import com.example.school_management.service.StudentService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/create-student")
    public ResponseDTO createStudent(@RequestBody final Student student) {
        return this.studentService.createStudent(student);
    }

    @GetMapping("/student/{id}")
    public ResponseDTO getStudentById(@PathVariable final String id) {
        return this.studentService.getStudentById(id);
    }

    @GetMapping("/retrieve-student")
    public ResponseDTO getStudentAll() {
        return this.studentService.getStudentAll();
    }

    @GetMapping("/retrieve-studentmarks/{id}")
    public ResponseDTO getMarksById(@PathVariable final String id) {
        return this.studentService.getMarksById(id);
    }

    @GetMapping("/retrieve-test/{id}")
    public ResponseDTO getStudentTest(@PathVariable final String id) {
        return this.studentService.studentTest(id);
    }

    @PutMapping("/update-student/{id}")
    public ResponseDTO updateStudent(@PathVariable final String id, @RequestBody final Student student) {
        return this.studentService.updateStudent(id, student);
    }

    @DeleteMapping("/remove-student/{id}")
    public ResponseDTO deleteStudent(@PathVariable final String id) {
        return this.studentService.deleteStudent(id);
    }

}
