package com.example.school_management.controller;

import com.example.school_management.dto.ResponseDTO;
import com.example.school_management.entity.Questions;
import com.example.school_management.entity.Tutor;
import com.example.school_management.service.TutorService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class TutorController {

    private final TutorService tutorService;

    public TutorController(TutorService tutorService) {
        this.tutorService = tutorService;
    }

    @PostMapping("/create-tutor")
    public ResponseDTO creteTutor(@RequestBody final Tutor tutor) {
        return this.tutorService.createTutor(tutor);
    }

    @PostMapping("/create-questionandchoices")
    public ResponseDTO createQuestion(@RequestBody final Questions questions) {
        return this.tutorService.addQuestionsAndChoices(questions);
    }

    @GetMapping("/tutor/{id}")
    public ResponseDTO getTutorById(@PathVariable final String id) {
        return this.tutorService.getTutorById(id);
    }

    @GetMapping("/retrieve-tutor")
    public ResponseDTO getAllTutor() {
        return this.tutorService.getAllTutor();
    }

    @GetMapping("/retrieve-marks")
    public ResponseDTO getAllMarks() {
        return this.tutorService.getAllMarks();
    }

    @PutMapping("/update-tutor/{id}")
    public ResponseDTO updateTutor(@PathVariable final String id, @RequestBody final Tutor tutor) {
        return this.tutorService.updateTutor(id, tutor);
    }

    @DeleteMapping("/remove-tutor/{id}")
    public ResponseDTO deleteTutor(@PathVariable final String id) {
        return this.tutorService.deleteTutor(id);
    }
}
