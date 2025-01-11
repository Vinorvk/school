package com.example.school_management.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.school_management.dto.ResponseDTO;
import com.example.school_management.entity.School;
import com.example.school_management.exception.UserNotFoundException;
import com.example.school_management.repository.SchoolRepository;

import statusResponse.Constants;

@Service
public class SchoolService {
	
	private SchoolRepository schoolRepository;
	
	public SchoolService(SchoolRepository schoolRepository) {
		this.schoolRepository = schoolRepository;
	}
	public ResponseDTO createSchool(final School school) {
		School createSchool = schoolRepository.save(school);
		return ResponseDTO.builder().message(Constants.CREATED).data(createSchool).statusCode(HttpStatus.CREATED.value()).build();	
	}
	public ResponseDTO getSchoolById(final String id) {
		School getSchoolId = schoolRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("Id not found"));
		return ResponseDTO.builder().message(Constants.RETRIEVED).data(getSchoolId).statusCode(HttpStatus.OK.value()).build();
	}
	public ResponseDTO getAllSchool() {
		List<School> getAllSchool = schoolRepository.findAll();
		return ResponseDTO.builder().message(Constants.RETRIEVED).data(getAllSchool).statusCode(HttpStatus.OK.value()).build();
	}
	public ResponseDTO updateSchool(final String id,final School school) {
		School updateSchool = schoolRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("Id not found"));
		updateSchool.setId(school.getId());
		updateSchool.setName(school.getName());
		updateSchool.setEmail(school.getEmail());
		updateSchool.setCity(school.getCity());
		this.schoolRepository.save(updateSchool);
		return ResponseDTO.builder().message(Constants.MODIFIED).data(updateSchool).statusCode(HttpStatus.OK.value()).build();		
	}
	public ResponseDTO deleteSchool(final String id) {
		School deleteSchool = schoolRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("Id not found"));
		this.schoolRepository.delete(deleteSchool);
		return ResponseDTO.builder().message(Constants.REMOVED).data(deleteSchool).statusCode(HttpStatus.OK.value()).build();
	}

}
