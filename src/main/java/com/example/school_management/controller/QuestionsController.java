package com.example.school_management.controller;

import com.example.school_management.dto.QuestionDTO;
import com.example.school_management.dto.ResponseDTO;
import com.example.school_management.entity.Questions;
import com.example.school_management.service.QuestionsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class QuestionsController {

    private final QuestionsService questionService;

    public QuestionsController(QuestionsService questionsService) {
        this.questionService = questionsService;
    }

    @PostMapping("/create-question")
    public ResponseDTO createQuestion(@RequestBody final Questions question) {
        return this.questionService.createQuestion(question);
    }

    @GetMapping("/question/{id}")
    public ResponseDTO getQuestionById(@PathVariable final String id) {
        return this.questionService.getQuestionById(id);
    }

    @GetMapping("/retrieve-question")
    public ResponseDTO getAllQuestion() {
        return this.questionService.getAllQuestion();
    }

    @GetMapping("/retrieve-questions/{id}")
    public QuestionDTO getQuestionsById(@PathVariable String id) {
        return this.questionService.getQuestionsById(id);
    }

    @GetMapping("/retrieve-allquestions")
    public List<QuestionDTO> getAllQuestions() {
        return this.questionService.getAllQuestions();
    }

    @PutMapping("/update-question/{id}")
    public ResponseDTO updateQuestion(@PathVariable final String id, @RequestBody final Questions question) {
        return this.questionService.updateQuestion(id, question);
    }

    @DeleteMapping("/remove-question/{id}")
    public ResponseDTO deleteQuestion(@PathVariable final String id) {
        return this.questionService.deleteQuestion(id);
    }
}