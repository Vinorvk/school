package com.example.school_management.service;

import com.example.school_management.dto.ResponseDTO;
import com.example.school_management.entity.Course;
import com.example.school_management.exception.UserNotFoundException;
import com.example.school_management.repository.CourseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import statusResponse.Constants;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public ResponseDTO createCourse(final Course course) {
        final Course createCourse = this.courseRepository.save(course);
        return ResponseDTO.builder().message(Constants.CREATED).data(createCourse).statusCode(HttpStatus.CREATED.value()).build();
    }

    public ResponseDTO getCourseById(final String id) {
        final Course course = this.courseRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Course Id not found:" + id));
        return ResponseDTO.builder().message(Constants.RETRIEVED).data(course).statusCode(HttpStatus.OK.value()).build();
    }

    public ResponseDTO getAllCourse() {
        final List<Course> getAllStudent = this.courseRepository.findAll();
        return ResponseDTO.builder().message(Constants.RETRIEVED).data(getAllStudent).statusCode(HttpStatus.OK.value()).build();
    }

    public ResponseDTO updateCourse(final String id, final Course course) {
        final Course updateCourse = this.courseRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Course Id not found:" + id));
        updateCourse.setCourses(course.getCourses());
        updateCourse.setSchool(course.getSchool());
        updateCourse.setStudent(course.getStudent());
        updateCourse.setTutor(course.getTutor());
        this.courseRepository.save(updateCourse);
        return ResponseDTO.builder().message(Constants.MODIFIED).data(updateCourse).statusCode(HttpStatus.OK.value()).build();
    }

    public ResponseDTO deleteCourse(final String id) {
        final Course deleteCourse = this.courseRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Course Id not found:" + id));
        this.courseRepository.delete(deleteCourse);
        return ResponseDTO.builder().message(Constants.REMOVED).data(deleteCourse).statusCode(HttpStatus.OK.value()).build();
    }

}
