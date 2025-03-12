package com.example.school_management.service;

import com.example.school_management.dto.MarkDTO;
import com.example.school_management.dto.ResponseDTO;
import com.example.school_management.entity.Questions;
import com.example.school_management.entity.Student;
import com.example.school_management.entity.Studentanswer;
import com.example.school_management.entity.Tutor;
import com.example.school_management.exception.UserNotFoundException;
import com.example.school_management.repository.QuestionsRepository;
import com.example.school_management.repository.StudentAnswerRepository;
import com.example.school_management.repository.StudentRepository;
import com.example.school_management.repository.TutorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import statusResponse.Constants;

import java.util.ArrayList;
import java.util.List;

@Service
public class TutorService {
    private final TutorRepository tutorRepository;
    private final StudentRepository studentRepository;
    private final StudentAnswerRepository studentanswerRepository;
    private final QuestionsRepository questionRepository;

    public TutorService(TutorRepository tutorRepository, StudentRepository studentRepository, StudentAnswerRepository studentanswerRepository, QuestionsRepository questionRepository) {
        this.tutorRepository = tutorRepository;
        this.studentRepository = studentRepository;
        this.studentanswerRepository = studentanswerRepository;
        this.questionRepository = questionRepository;
    }

    public ResponseDTO createTutor(final Tutor tutor) {
        final Tutor createTutor = this.tutorRepository.save(tutor);
        return ResponseDTO.builder().message(Constants.CREATED).data(createTutor).statusCode(HttpStatus.CREATED.value()).build();
    }

    public ResponseDTO addQuestionsAndChoices(final Questions questions) {
        final Questions question = this.questionRepository.save(questions);
        return ResponseDTO.builder().message(Constants.CREATED).data(question).statusCode(HttpStatus.CREATED.value()).build();
    }

    public ResponseDTO getTutorById(final String id) {
        final Tutor tutor = this.tutorRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Tutor Id not found:" + id));
        return ResponseDTO.builder().message(Constants.RETRIEVED).data(tutor).statusCode(HttpStatus.OK.value()).build();
    }

    public ResponseDTO getAllTutor() {
        final List<Tutor> getAllTutor = this.tutorRepository.findAll();
        return ResponseDTO.builder().message(Constants.RETRIEVED).data(getAllTutor).statusCode(HttpStatus.OK.value()).build();
    }

    public int evaluateMarks(final String id) {
        final List<Studentanswer> answers = this.studentanswerRepository.findByStudentId(id);
        int marks = 0;
        for (Studentanswer studentanswer : answers) {
            Questions questions = studentanswer.getQuestion();
            if (questions != null && questions.getCorrect_answer() != null && studentanswer.getStudent_answer() != null) {
                if (studentanswer.getStudent_answer().equals(questions.getCorrect_answer())) {
                    marks++;
                }
            }
        }
        return marks;
    }

    public ResponseDTO getAllMarks() {
        final List<Student> students = this.studentRepository.findAll();
        final List<MarkDTO> studentMarks = new ArrayList<>();

        for (Student student : students) {
            String studentId = student.getId();
            String studentName = student.getName();
            int marks = evaluateMarks(studentId);

            MarkDTO studentMarksDto = new MarkDTO(studentId, studentName, marks);
            studentMarks.add(studentMarksDto);
        }
        return ResponseDTO.builder().message(Constants.RETRIEVED).data(studentMarks).statusCode(HttpStatus.OK.value()).build();
    }

    public ResponseDTO updateTutor(final String id, final Tutor tutor) {
        final Tutor updateTutor = this.tutorRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Tutor Id not found:" + id));
        updateTutor.setTutor_name(tutor.getTutor_name());
        updateTutor.setSchool(tutor.getSchool());
        this.tutorRepository.save(updateTutor);
        return ResponseDTO.builder().message(Constants.MODIFIED).data(updateTutor).statusCode(HttpStatus.OK.value()).build();
    }

    public ResponseDTO deleteTutor(final String id) {
        final Tutor deleTutor = this.tutorRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Tutor Id not found:" + id));
        this.tutorRepository.delete(deleTutor);
        return ResponseDTO.builder().message(Constants.REMOVED).data(deleTutor).statusCode(HttpStatus.OK.value()).build();
    }
}