package com.example.school_management.controller;

import com.example.school_management.dto.ResponseDTO;
import com.example.school_management.entity.Course;
import com.example.school_management.service.CourseService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/create-course")
    public ResponseDTO createCourse(@RequestBody final Course course) {
        return this.courseService.createCourse(course);
    }

    @GetMapping("/course/{id}")
    public ResponseDTO getCourseById(@PathVariable final String id) {
        return this.courseService.getCourseById(id);
    }

    @GetMapping("/retrieve-course")
    public ResponseDTO getAllCourse() {
        return this.courseService.getAllCourse();
    }

    @PutMapping("/update-course/{id}")
    public ResponseDTO updateCourse(@PathVariable final String id, @RequestBody final Course course) {
        return this.courseService.updateCourse(id, course);
    }

    @DeleteMapping("/remove-course/{id}")
    public ResponseDTO deleteCourse(@PathVariable final String id) {
        return this.courseService.deleteCourse(id);
    }
}
