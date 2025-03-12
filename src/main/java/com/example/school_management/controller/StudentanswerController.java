package com.example.school_management.controller;

import com.example.school_management.dto.AnswerDTO;
import com.example.school_management.dto.ResponseDTO;
import com.example.school_management.entity.Studentanswer;
import com.example.school_management.service.StudentAnswerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentanswerController {

    private final StudentAnswerService studentAnswerService;

    public StudentanswerController(StudentAnswerService studentanswerService) {
        this.studentAnswerService = studentanswerService;
    }

    @PostMapping("/create-studentanswer")
    public ResponseDTO createStudentAnswer(@RequestBody final Studentanswer studentAnswer) {
        return this.studentAnswerService.createStudentAnswer(studentAnswer);
    }

    @GetMapping("/studentanswer/{id}")
    public ResponseDTO getStudentAnswerById(@PathVariable final String id) {
        return this.studentAnswerService.getStudentAnswerById(id);
    }

    @GetMapping("/retrieve-studentanswer")
    public ResponseDTO getAllStudentAnswer() {
        return this.studentAnswerService.getAllStudentAnswer();
    }

    @GetMapping("/retrieve-answers/{id}")
    public AnswerDTO getAnswerById(@PathVariable final String id) {
        return this.studentAnswerService.getAnswerById(id);
    }

    @GetMapping("/retrieve-allanswers")
    public List<AnswerDTO> getAllAnswers() {
        return this.studentAnswerService.getAllAnswers();
    }

    @PutMapping("/update-studentanswer/{id}")
    public ResponseDTO updateStudentAnswer(@PathVariable final String id, @RequestBody final Studentanswer studentanswer) {
        return this.studentAnswerService.updateStudentAnswer(id, studentanswer);
    }

    @DeleteMapping("/remove-studentanswer/{id}")
    public ResponseDTO deleteStudentAnswer(@PathVariable final String id) {
        return this.studentAnswerService.deleteStudentAnswer(id);
    }

}
