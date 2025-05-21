package com.example.school_management.controller;

import com.example.school_management.dto.ResponseDTO;
import com.example.school_management.entity.School;
import com.example.school_management.service.SchoolService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/school")
public class SchoolController {
    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping("/create")
    public ResponseDTO createSchool(@RequestBody final School school) {
        return this.schoolService.createSchool(school);
    }

    @GetMapping("/retrieve/{id}")
    public ResponseDTO getSchoolById(@PathVariable final String id) {
        return this.schoolService.retrieveSchoolById(id);
    }

    @GetMapping("/retrieve-all")
    public ResponseDTO getAllSchool() {
        return this.schoolService.retrieveAllSchool();
    }

    @PutMapping("/update/{id}")
    public ResponseDTO updateSchool(@PathVariable final String id, @RequestBody final School school) {
        return this.schoolService.updateSchool(id, school);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseDTO deleteSchool(@PathVariable final String id) {
        return this.schoolService.removeSchool(id);
    }

    @GetMapping("/retrieve-school/{id}")
    public ResponseDTO getSchoolDetails(@PathVariable final String id) {
        return this.schoolService.retrieveSchoolDetails(id);
    }

    @GetMapping("/retrieve-page")
    public ResponseDTO retrieveSchoolByPage(@RequestParam final int pageNumber,
                                            @RequestParam final int pageSize,
                                            @RequestParam final boolean sort,
                                            @RequestParam final String field,
                                            @RequestParam final String search) {
        return this.schoolService.getSchoolByPage(pageNumber, pageSize, sort, field, search);
    }

}