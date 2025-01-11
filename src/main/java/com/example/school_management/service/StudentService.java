package com.example.school_management.service;

import java.util.List;

import org.springframework.http.HttpStatus;

//import java.util.List;
//import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.school_management.dto.ResponseDTO;
import com.example.school_management.entity.School;
import com.example.school_management.entity.Student;
import com.example.school_management.exception.UserNotFoundException;
import com.example.school_management.repository.StudentRepository;

import statusResponse.Constants;

@Service
public class StudentService {
	
	private StudentRepository studentRepository;
	
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public ResponseDTO createStudent(final Student student) {  
		Student createStudent = studentRepository.save(student);
		return ResponseDTO.builder().message(Constants.CREATED).data(createStudent).statusCode(HttpStatus.CREATED.value()).build();
	}

	public ResponseDTO getStudentById(final String id) {
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("Id not found"));
		return ResponseDTO.builder().message(Constants.RETRIEVED).data(student).statusCode(HttpStatus.OK.value()).build();
	}

	public ResponseDTO getStudentAll() {
		List<Student> getAllStudent = studentRepository.findAll();
		return ResponseDTO.builder().message(Constants.RETRIEVED).data(getAllStudent).statusCode(HttpStatus.OK.value()).build();
	}

	public ResponseDTO updateStudent(final String id, final Student student) {
		
		Student studentDetails = studentRepository.findById(id)
				.orElseThrow(()-> new UserNotFoundException("Id not found"));
		studentDetails.setId(student.getId());
		studentDetails.setName(student.getName());
		studentDetails.setSchool_name(student.getSchool_name());
		this.studentRepository.save(studentDetails);
	    return ResponseDTO.builder().message(Constants.MODIFIED).data(studentDetails).statusCode(HttpStatus.OK.value()).build();
	}
	
	public ResponseDTO deleteStudent(final String id) {
		
		Student deleteStudents = studentRepository.findById(id)
				.orElseThrow(()-> new UserNotFoundException("Id not found"));
	    this.studentRepository.delete(deleteStudents);
	    return ResponseDTO.builder().message(Constants.REMOVED).data(deleteStudents).statusCode(HttpStatus.OK.value()).build();
	}
}
