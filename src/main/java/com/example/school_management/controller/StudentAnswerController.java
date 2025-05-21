package com.example.school_management.controller;

import com.example.school_management.dto.ResponseDTO;
import com.example.school_management.entity.Studentanswer;
import com.example.school_management.service.StudentAnswerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/studentanswer")
public class StudentAnswerController {
    private final StudentAnswerService studentAnswerService;

    public StudentAnswerController(final StudentAnswerService studentanswerService) {
        this.studentAnswerService = studentanswerService;
    }

    @PostMapping("/create")
    public ResponseDTO createStudentAnswer(@RequestBody final Studentanswer studentAnswer) {
        return this.studentAnswerService.createStudentAnswer(studentAnswer);
    }

    @GetMapping("/retrieve/{id}")
    public ResponseDTO getStudentAnswerById(@PathVariable final String id) {
        return this.studentAnswerService.retrieveStudentAnswerById(id);
    }

    @GetMapping("/retrieve-all")
    public ResponseDTO getAllStudentAnswer() {
        return this.studentAnswerService.retrieveAllStudentAnswer();
    }

//    @GetMapping("/retrieve-answers/{id}")
//    public AnswerDTO getAnswerById(@PathVariable final String id) {
//        return this.studentAnswerService.retrieveAnswerById(id);
//    }

//    @GetMapping("/retrieve-allanswers")
//    public List<AnswerDTO> getAllAnswers() {
//        return this.studentAnswerService.retrieveAllAnswers();
//    }

    @PutMapping("/update/{id}")
    public ResponseDTO updateStudentAnswer(@PathVariable final String id, @RequestBody final Studentanswer studentanswer) {
        return this.studentAnswerService.updateStudentAnswer(id, studentanswer);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseDTO deleteStudentAnswer(@PathVariable final String id) {
        return this.studentAnswerService.removeStudentAnswer(id);
    }

    //    @GetMapping("/page")
//    public ResponseDTO getStudentAnswerByPage(@RequestParam int index,@RequestParam int size,@RequestParam String field){
//        return this.studentAnswerService.getStudentAnswerByPage(index,size,field);
//    }
    @GetMapping("/get-page")
    public ResponseDTO retireveStudentAnswerByPage(@RequestParam final int pageNumber,
                                                   @RequestParam final int pageSize,
                                                   @RequestParam final boolean order,
                                                   @RequestParam final String field) {
        return this.studentAnswerService.getAnswerByPage(pageNumber, pageSize, order, field);
    }

}