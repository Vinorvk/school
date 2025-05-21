package com.example.school_management.service;

import com.example.school_management.dto.ResponseDTO;
import com.example.school_management.entity.Questions;
import com.example.school_management.entity.Student;
import com.example.school_management.entity.Studentanswer;
import com.example.school_management.exception.UserNotFoundException;
import com.example.school_management.repository.QuestionsRepository;
import com.example.school_management.repository.StudentAnswerRepository;
import com.example.school_management.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import utilities.Constants;

import java.util.List;

@Service
public class StudentAnswerService {
    private final StudentAnswerRepository studentanswerRepository;
    private final StudentRepository studentRepository;
    private final QuestionsRepository questionsRepository;

    public StudentAnswerService(final StudentAnswerRepository studentanswerRepository, StudentRepository studentRepository, QuestionsRepository questionsRepository) {
        this.studentanswerRepository = studentanswerRepository;
        this.studentRepository = studentRepository;
        this.questionsRepository = questionsRepository;
    }

    public ResponseDTO createStudentAnswer(Studentanswer studentAnswer) {
        Student student = this.studentRepository.findById(studentAnswer.getStudent().getId())
                .orElseThrow(() -> new UserNotFoundException("Student Id not found"));
        studentAnswer.setStudent(student);
        String questionId = studentAnswer.getQuestion().getId();
        Questions question = this.questionsRepository.findById(questionId)
                .orElseThrow(() -> new UserNotFoundException("Question not found"));
        String answer = studentAnswer.getStudent_answer();
        String option1 = question.getOption1();
        String option2 = question.getOption2();
        String option3 = question.getOption3();
        if (answer.contains(option1) || answer.contains(option2) || answer.contains(option3)) {
            this.studentanswerRepository.save(studentAnswer);
            return ResponseDTO.builder().message(Constants.CREATED).statusCode(HttpStatus.OK.value()).data(studentAnswer).build();
        } else {
            throw new UserNotFoundException("answer does not match with options.");
        }
    }

    public ResponseDTO retrieveStudentAnswerById(final String id) {
        final Studentanswer studentAnswer = this.studentanswerRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("StudentAnswer Id not found:" + id));
        return ResponseDTO.builder().message(Constants.RETRIEVED).data(studentAnswer).statusCode(HttpStatus.OK.value()).build();
    }

    public ResponseDTO retrieveAllStudentAnswer() {
        final List<Studentanswer> getStudentAnswer = this.studentanswerRepository.findAll();
        return ResponseDTO.builder().message(Constants.RETRIEVED).data(getStudentAnswer).statusCode(HttpStatus.OK.value()).build();
    }

//    public AnswerDTO retrieveAnswerById(final String id) {
//        final Studentanswer answer = this.studentanswerRepository.findById(id)
//                .orElseThrow(() -> new UserNotFoundException("StudentAnswer Id not found:" + id));
//        return AnswerDTO.builder().student_answer(answer.getStudent_answer()).build();
//    }

//    public List<AnswerDTO> retrieveAllAnswers() {
//        final List<Studentanswer> answers = this.studentanswerRepository.findAll();
//        return answers.stream()
//                .map(answer -> AnswerDTO.builder()
//                        .student_answer(answer.getStudent_answer())
//                        .build())
//                .collect(Collectors.toList());
//    }

    public ResponseDTO updateStudentAnswer(final String id, final Studentanswer studentanswer) {
        final Studentanswer answer = this.studentanswerRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("StudentAnswer Id not found:" + id));
        studentanswer.setStudent_answer(answer.getStudent_answer());
        studentanswer.setStudent(answer.getStudent());
        studentanswer.setQuestion(answer.getQuestion());
        this.studentanswerRepository.save(answer);
        return ResponseDTO.builder().message(Constants.MODIFIED).data(answer).statusCode(HttpStatus.OK.value()).build();
    }

    public ResponseDTO removeStudentAnswer(final String id) {
        final Studentanswer deleteAnswer = this.studentanswerRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("StudentAnswer Id not found:" + id));
        this.studentanswerRepository.save(deleteAnswer);
        return ResponseDTO.builder().message(Constants.REMOVED).data(deleteAnswer).statusCode(HttpStatus.OK.value()).build();
    }

    //    public ResponseDTO getStudentAnswerByPage(int index, int size, String field) {
//        Sort sort = Sort.by(Sort.Direction.ASC, field);
//        Pageable page = PageRequest.of(index, size, sort);
//        Page<Studentanswer> answer = studentanswerRepository.findAll(page);
//        return ResponseDTO.builder().message(Constants.RETRIEVED).data(answer).statusCode(HttpStatus.OK.value()).build();
//    }
    public ResponseDTO getAnswerByPage(final int pageNumber, final int pageSize, final boolean order, final String field) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(order ? Sort.Direction.ASC : Sort.Direction.DESC, field));
        Page<Studentanswer> answers = this.studentanswerRepository.findAll(pageable);
        return ResponseDTO.builder().message(Constants.RETRIEVED).data(answers).statusCode(HttpStatus.OK.value()).build();
    }
}