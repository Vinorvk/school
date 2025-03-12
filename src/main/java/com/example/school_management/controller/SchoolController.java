package com.example.school_management.controller;

import com.example.school_management.dto.ResponseDTO;
import com.example.school_management.entity.School;
import com.example.school_management.service.SchoolService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class SchoolController {

    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping("/create-school")
    public ResponseDTO createSchool(@RequestBody final School school) {
        return this.schoolService.createSchool(school);
    }

    @GetMapping("/school/{id}")
    public ResponseDTO getSchoolById(@PathVariable final String id) {
        return this.schoolService.getSchoolById(id);
    }

    @GetMapping("/retrieve-school")
    public ResponseDTO getAllSchool() {
        return this.schoolService.getAllSchool();
    }

    @PutMapping("/update-school/{id}")
    public ResponseDTO updateSchool(@PathVariable final String id, @RequestBody final School school) {
        return this.schoolService.updateSchool(id, school);
    }

    @DeleteMapping("/remove-school/{id}")
    public ResponseDTO deleteSchool(@PathVariable final String id) {
        return this.schoolService.deleteSchool(id);
    }

    @GetMapping("/details/{id}")
    public ResponseDTO getSchoolDetails(@PathVariable final String id) {
        return this.schoolService.getSchoolDetails(id);
    }
}