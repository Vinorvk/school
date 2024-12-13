package com.example.school_management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.school_management.entity.Student;
import com.example.school_management.repository.StudentRepository;

@Service
public class StudentService {
	
	private StudentRepository studentRepository;
	
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public Student createStudent(final Student student) {   
		return this.studentRepository.save(student);
	}

	public Optional<Student> getStudentById(final String id) {
		return this.studentRepository.findById(id);
	}

	public List<Student> getStudentAll() {
		return this.studentRepository.findAll();
	}

	public String updateStudent(final String id, final Student student) {
		Optional<Student> student1= studentRepository.findById(id);
	    if(student1.isPresent()) {
		student.setId(id);
	    studentRepository.save(student);	
	    return "updated sucessfully";
	}else {
		return "Student does not found";
	}
	}
	public String deleteStudent(final String id) {
	Optional<Student> students = studentRepository.findById(id);
	    if(students.isPresent()) {
	    studentRepository.deleteById(id);
	    return "deleted sucessfully";
	}else {
		return "Student does not found";
    }
}
}
	