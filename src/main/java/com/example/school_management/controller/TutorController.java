package com.example.school_management.controller;

import com.example.school_management.dto.ResponseDTO;
import com.example.school_management.entity.Questions;
import com.example.school_management.entity.Tutor;
import com.example.school_management.service.TutorService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tutor")
public class TutorController {
    private final TutorService tutorService;

    public TutorController(final TutorService tutorService) {
        this.tutorService = tutorService;
    }

    @PostMapping("/create")
    public ResponseDTO creteTutor(@RequestBody final Tutor tutor) {
        return this.tutorService.createTutor(tutor);
    }

    @PostMapping("/create-question-and-choices")
    public ResponseDTO createQuestion(@RequestBody final Questions questions) {
        return this.tutorService.addQuestionsAndChoices(questions);
    }

    @GetMapping("/retrieve/{id}")
    public ResponseDTO getTutorById(@PathVariable final String id) {
        return this.tutorService.retrieveTutorById(id);
    }

    @GetMapping("/retrieve-all")
    public ResponseDTO getAllTutor() {
        return this.tutorService.retrieveAllTutor();
    }

    @GetMapping("/retrieve-marks")
    public ResponseDTO getAllMarks() {
        return this.tutorService.retrieveAllMarks();
    }

    @PutMapping("/update/{id}")
    public ResponseDTO updateTutor(@PathVariable final String id, @RequestBody final Tutor tutor) {
        return this.tutorService.updateTutor(id, tutor);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseDTO deleteTutor(@PathVariable final String id) {
        return this.tutorService.removeTutor(id);
    }

    //    @GetMapping("/page")
//    public ResponseDTO getTutorByPage(@RequestParam int index, @RequestParam int size, @RequestParam String field) {
//        return this.tutorService.getTutorByPage(index, size, field);
//    }
//    @GetMapping("/get-page")
//    public ResponseDTO retrieveTutorByPage(@RequestParam final int pageNumber,
//                                           @RequestParam final int pageSize,
//                                           @RequestParam final boolean order,
//                                           @RequestParam final String field) {
//        return this.tutorService.getTutorByPage(pageNumber, pageSize, order, field);
//    }

}