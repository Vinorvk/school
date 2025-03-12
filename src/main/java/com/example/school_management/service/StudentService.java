package com.example.school_management.service;


import com.example.school_management.dto.MarkDTO;
import com.example.school_management.dto.ResponseDTO;
import com.example.school_management.entity.Questions;
import com.example.school_management.entity.Student;
import com.example.school_management.entity.Studentanswer;
import com.example.school_management.exception.UserNotFoundException;
import com.example.school_management.repository.StudentAnswerRepository;
import com.example.school_management.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import statusResponse.Constants;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentAnswerRepository studentanswerRepository;

    public StudentService(StudentRepository studentRepository, StudentAnswerRepository studentanswerRepository) {
        this.studentRepository = studentRepository;
        this.studentanswerRepository = studentanswerRepository;
    }

    public ResponseDTO createStudent(final Student student) {
        final Student createStudent = this.studentRepository.save(student);
        return ResponseDTO.builder().message(Constants.CREATED).data(createStudent).statusCode(HttpStatus.CREATED.value()).build();
    }

    public ResponseDTO getStudentById(final String id) {
        final Student student = this.studentRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Student Id not found:" + id));
        return ResponseDTO.builder().message(Constants.RETRIEVED).data(student).statusCode(HttpStatus.OK.value()).build();
    }

    public ResponseDTO getStudentAll() {
        final List<Student> getAllStudent = this.studentRepository.findAll();
        return ResponseDTO.builder().message(Constants.RETRIEVED).data(getAllStudent).statusCode(HttpStatus.OK.value()).build();
    }

    public ResponseDTO getMarksById(String id) {
        final Student student = this.studentRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Student Id not found:" + id));
        final List<Studentanswer> studentanswers = this.studentanswerRepository.findByStudentId(id);
        int marks = 0;
        for (Studentanswer answer : studentanswers) {
            Questions questions = answer.getQuestion();
            if (questions != null && questions.getCorrect_answer() != null && answer.getStudent_answer() != null) {
                if (answer.getStudent_answer().equals(questions.getCorrect_answer())) {
                    marks++;
                }
            }
        }
        MarkDTO markdto = new MarkDTO(student.getId(), student.getName(), marks);
        return ResponseDTO.builder().message(Constants.RETRIEVED).data(markdto).statusCode(HttpStatus.OK.value()).build();
    }

    public ResponseDTO studentTest(String id) {
        final Student student = this.studentRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Student Id not found:" + id));
        this.studentRepository.save(student);
        final List<Studentanswer> studentanswers = this.studentanswerRepository.findByStudentId(id);
        return ResponseDTO.builder().message(Constants.RETRIEVED).data(studentanswers).statusCode(HttpStatus.OK.value()).build();
    }

    public ResponseDTO updateStudent(final String id, final Student student) {
        final Student studentDetails = this.studentRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Student Id not found:" + id));
        studentDetails.setName(student.getName());
        studentDetails.setSchool(student.getSchool());
        this.studentRepository.save(studentDetails);
        return ResponseDTO.builder().message(Constants.MODIFIED).data(studentDetails).statusCode(HttpStatus.OK.value()).build();
    }

    public ResponseDTO deleteStudent(final String id) {
        final Student deleteStudents = this.studentRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Student Id not found:" + id));
        this.studentRepository.delete(deleteStudents);
        return ResponseDTO.builder().message(Constants.REMOVED).data(deleteStudents).statusCode(HttpStatus.OK.value()).build();
    }
}