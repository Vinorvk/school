package com.example.school_management.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDTO {
	
	private String message;
	private Object data;
	private Integer statusCode;
	
}
