package com.example.school_management.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuestionDTO {
	
	private String id;
	private String question;
	private String option1;
	private String option2;
	private String option3;

}
