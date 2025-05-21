package com.example.school_management.controller;

import com.example.school_management.dto.ResponseDTO;
import com.example.school_management.entity.Course;
import com.example.school_management.service.CourseService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
public class CourseController {
    private final CourseService courseService;

    public CourseController(final CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/create")
    public ResponseDTO createCourse(@RequestBody final Course course) {
        return this.courseService.createCourse(course);
    }

    @GetMapping("/retrieve/{id}")
    public ResponseDTO getCourseById(@PathVariable final String id) {
        return this.courseService.retrieveCourseById(id);
    }

    @GetMapping("/retrieve-all")
    public ResponseDTO getAllCourse() {
        return this.courseService.retrieveAllCourse();
    }

    @PutMapping("/update/{id}")
    public ResponseDTO updateCourse(@PathVariable final String id, @RequestBody final Course course) {
        return this.courseService.updateCourse(id, course);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseDTO deleteCourse(@PathVariable final String id) {
        return this.courseService.removeCourse(id);
    }


}