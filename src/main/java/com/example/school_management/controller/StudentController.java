package com.example.school_management.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.school_management.dto.ResponseDTO;
import com.example.school_management.entity.Student;
import com.example.school_management.service.StudentService;

@RestController
@RequestMapping("/api")
public class StudentController {
	
	private StudentService studentService;
	
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
	public ResponseDTO getStudentAll(){
		return this.studentService.getStudentAll();
	}
	@PutMapping("/update-student/{id}")
	public ResponseDTO updateStudent(@PathVariable final String id,@RequestBody final Student student) {
		return this.studentService.updateStudent(id,student);
	}
	@DeleteMapping("/remove-student/{id}")
	public ResponseDTO deleteStudent(@PathVariable final String id) {
		return this.studentService.deleteStudent(id);
	}
}
