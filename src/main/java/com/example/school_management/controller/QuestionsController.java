package com.example.school_management.controller;

import com.example.school_management.dto.ResponseDTO;
import com.example.school_management.entity.Questions;
import com.example.school_management.service.QuestionsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/question")
public class QuestionsController {
    private final QuestionsService questionService;

    public QuestionsController(final QuestionsService questionsService) {
        this.questionService = questionsService;
    }

    @PostMapping("/create")
    public ResponseDTO createQuestion(@RequestBody final Questions question) {
        return this.questionService.createQuestion(question);
    }

    @GetMapping("/retrieve/{id}")
    public ResponseDTO getQuestionById(@PathVariable final String id) {
        return this.questionService.retrieveQuestionById(id);
    }

    @GetMapping("/retrieve-all")
    public ResponseDTO getAllQuestion() {
        return this.questionService.retrieveAllQuestion();
    }

//    @GetMapping("/retrieve/{id}")
//    public QuestionDTO getQuestionsById(@PathVariable String id) {
//        return this.questionService.retrieveQuestionsById(id);
//    }
//
//    @GetMapping("/retrieve-all")
//    public List<QuestionDTO> getAllQuestions() {
//        return this.questionService.retrieveAllQuestions();
//    }

    @PutMapping("/update/{id}")
    public ResponseDTO updateQuestion(@PathVariable final String id, @RequestBody final Questions question) {
        return this.questionService.updateQuestion(id, question);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseDTO deleteQuestion(@PathVariable final String id) {
        return this.questionService.removeQuestion(id);
    }

    //    @GetMapping("/page")
//    public ResponseDTO getQuestionByPage(@RequestParam int index,@RequestParam int size,@RequestParam String field){
//        return this.questionService.getQuestionByPage(index,size,field);
//    }
    @GetMapping("/retrieve-page")
    public ResponseDTO retrieveQuestionByPage(@RequestParam final int pageNumber,
                                              @RequestParam final int pageSize,
                                              @RequestParam final boolean sort,
                                              @RequestParam final String field,
                                              @RequestParam final  String search) {
        return this.questionService.getQuestionByPage(pageNumber,pageSize,sort,field,search);
    }
}