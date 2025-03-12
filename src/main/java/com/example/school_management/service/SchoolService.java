package com.example.school_management.service;

import com.example.school_management.dto.ResponseDTO;
import com.example.school_management.dto.SchoolDTO;
import com.example.school_management.entity.School;
import com.example.school_management.entity.Student;
import com.example.school_management.entity.Tutor;
import com.example.school_management.exception.UserNotFoundException;
import com.example.school_management.repository.SchoolRepository;
import com.example.school_management.repository.StudentRepository;
import com.example.school_management.repository.TutorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import statusResponse.Constants;

import java.util.List;

@Service
public class SchoolService {

    private final SchoolRepository schoolRepository;
    private final StudentRepository studentRepository;
    private final TutorRepository tutorRepository;

    public SchoolService(SchoolRepository schoolRepository, StudentRepository studentRepository, TutorRepository tutorRepository) {
        this.schoolRepository = schoolRepository;
        this.studentRepository = studentRepository;
        this.tutorRepository = tutorRepository;
    }

    public ResponseDTO createSchool(final School school) {
        final School createSchool = this.schoolRepository.save(school);
        return ResponseDTO.builder().message(Constants.CREATED).data(createSchool).statusCode(HttpStatus.CREATED.value()).build();
    }

    public ResponseDTO getSchoolById(final String id) {
        final School getSchoolId = this.schoolRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("School Id not found:" + id));
        return ResponseDTO.builder().message(Constants.RETRIEVED).data(getSchoolId).statusCode(HttpStatus.OK.value()).build();
    }

    public ResponseDTO getAllSchool() {
        final List<School> getAllSchool = this.schoolRepository.findAll();
        return ResponseDTO.builder().message(Constants.RETRIEVED).data(getAllSchool).statusCode(HttpStatus.OK.value()).build();
    }

    public ResponseDTO updateSchool(final String id, final School school) {
        final School updateSchool = this.schoolRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("School Id not found:" + id));
        updateSchool.setName(school.getName());
        updateSchool.setEmail(school.getEmail());
        updateSchool.setCity(school.getCity());
        this.schoolRepository.save(updateSchool);
        return ResponseDTO.builder().message(Constants.MODIFIED).data(updateSchool).statusCode(HttpStatus.OK.value()).build();
    }

    public ResponseDTO deleteSchool(final String id) {
        final School deleteSchool = this.schoolRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("School Id not found:" + id));
        this.schoolRepository.delete(deleteSchool);
        return ResponseDTO.builder().message(Constants.REMOVED).data(deleteSchool).statusCode(HttpStatus.OK.value()).build();
    }

    public ResponseDTO getSchoolDetails(final String id) {
        final School schools = this.schoolRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("School Id not found:" + id));
        this.schoolRepository.save(schools);
        final List<Student> students = studentRepository.findBySchoolId(id);
        final List<Tutor> tutors = tutorRepository.findBySchoolId(id);
        SchoolDTO schoolDTO = new SchoolDTO(id, students, tutors);
        return ResponseDTO.builder().message(Constants.RETRIEVED).data(schoolDTO).statusCode(HttpStatus.OK.value()).build();
    }
}