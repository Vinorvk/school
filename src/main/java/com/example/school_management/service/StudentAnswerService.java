package com.example.school_management.service;

import com.example.school_management.dto.AnswerDTO;
import com.example.school_management.dto.ResponseDTO;
import com.example.school_management.entity.Student;
import com.example.school_management.entity.Studentanswer;
import com.example.school_management.exception.UserNotFoundException;
import com.example.school_management.repository.StudentAnswerRepository;
import com.example.school_management.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import statusResponse.Constants;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentAnswerService {

    private final StudentAnswerRepository studentanswerRepository;
    private final StudentRepository studentRepository;

    public StudentAnswerService(StudentAnswerRepository studentanswerRepository, StudentRepository studentRepository) {
        this.studentanswerRepository = studentanswerRepository;
        this.studentRepository = studentRepository;
    }

    public ResponseDTO createStudentAnswer(final Studentanswer studentAnswer) {
        final Student students = this.studentRepository.findById(studentAnswer.getStudent().getId())
                .orElseThrow(() -> new UserNotFoundException("Student Id not found"));
        studentAnswer.setStudent(students);
        this.studentanswerRepository.save(studentAnswer);
        return ResponseDTO.builder().message(Constants.CREATED).data(studentAnswer).statusCode(HttpStatus.OK.value()).build();
    }

    public ResponseDTO getStudentAnswerById(final String id) {
        final Studentanswer studentAnswer = this.studentanswerRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("StudentAnswer Id not found:" + id));
        return ResponseDTO.builder().message(Constants.RETRIEVED).data(studentAnswer).statusCode(HttpStatus.OK.value()).build();
    }

    public ResponseDTO getAllStudentAnswer() {
        final List<Studentanswer> getStudentAnswer = this.studentanswerRepository.findAll();
        return ResponseDTO.builder().message(Constants.RETRIEVED).data(getStudentAnswer).statusCode(HttpStatus.OK.value()).build();
    }

    public AnswerDTO getAnswerById(final String id) {
        final Studentanswer answer = this.studentanswerRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("StudentAnswer Id not found:" + id));
        return AnswerDTO.builder().student_answer(answer.getStudent_answer()).build();
    }

    public List<AnswerDTO> getAllAnswers() {
        final List<Studentanswer> answers = this.studentanswerRepository.findAll();
        return answers.stream()
                .map(answer -> AnswerDTO.builder()
                        .student_answer(answer.getStudent_answer())
                        .build())
                .collect(Collectors.toList());
    }

    public ResponseDTO updateStudentAnswer(final String id, final Studentanswer studentanswer) {
        final Studentanswer answer = this.studentanswerRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("StudentAnswer Id not found:" + id));
        studentanswer.setStudent_answer(answer.getStudent_answer());
        studentanswer.setStudent(answer.getStudent());
        studentanswer.setQuestion(answer.getQuestion());
        this.studentanswerRepository.save(answer);
        return ResponseDTO.builder().message(Constants.MODIFIED).data(answer).statusCode(HttpStatus.OK.value()).build();
    }

    public ResponseDTO deleteStudentAnswer(final String id) {
        final Studentanswer deleteAnswer = this.studentanswerRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("StudentAnswer Id not found:" + id));
        this.studentanswerRepository.save(deleteAnswer);
        return ResponseDTO.builder().message(Constants.REMOVED).data(deleteAnswer).statusCode(HttpStatus.OK.value()).build();
    }
}