package com.example.school_management.controller;

import com.example.school_management.dto.ResponseDTO;
import com.example.school_management.entity.Student;
import com.example.school_management.service.StudentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(final StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/create")
    public ResponseDTO createStudent(@RequestBody final Student student) {
        return this.studentService.createStudent(student);
    }

    @GetMapping("/retrieve/{id}")
    public ResponseDTO getStudentById(@PathVariable final String id) {
        return this.studentService.retrieveStudentById(id);
    }

    @GetMapping("/retrieve-all")
    public ResponseDTO getStudentAll() {
        return this.studentService.retrieveStudentAll();
    }

    @GetMapping("/retrieve-studentmarks/{id}")
    public ResponseDTO getMarksById(@PathVariable final String id) {
        return this.studentService.retrieveMarksById(id);
    }

    @GetMapping("/retrieve-test/{id}")
    public ResponseDTO getStudentTest(@PathVariable final String id) {
        return this.studentService.retrieveStudentTest(id);
    }

    @PutMapping("/update/{id}")
    public ResponseDTO updateStudent(@PathVariable final String id, @RequestBody final Student student) {
        return this.studentService.updateStudent(id, student);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseDTO deleteStudent(@PathVariable final String id) {
        return this.studentService.removeStudent(id);
    }

    //    @GetMapping("/page")
//    public ResponseDTO getStudentByPage(@RequestParam int index,@RequestParam int size,@RequestParam String field){
//        return this.studentService.getStudentByPage(index,size,field);
//    }
    @GetMapping("/retrieve-page")
    public ResponseDTO retrieveStudentByPage(@RequestParam(defaultValue = "0") final int pageNumber,
                                             @RequestParam(defaultValue = "10") final int pageSize,
                                             @RequestParam(defaultValue = "true") final boolean sort,
                                             @RequestParam(defaultValue = "name") final String field,
                                             @RequestParam(defaultValue = "") final String search) {
        return this.studentService.getStudentByPage(pageNumber, pageSize, sort, field, search);
    }

}