package com.example.school_management.dto;


import java.util.Date;

import lombok.Data;

@Data
public class MessageresponseDTO {
	
	private String message;
	private Date timestamp;
	private Integer statusCode;
	private String description;
	
	public MessageresponseDTO(Integer statusCode, Date timestamp, String message, String description) {
		this.message = message;
		this.timestamp = timestamp;
		this.statusCode = statusCode;
		this.description = description;
	}
	
	
//
//	public int getStatusCode() {
//		return statusCode;
//	}
//
//	public void setStatusCode(int statusCode) {
//		this.statusCode = statusCode;
//	}
//
//	public Date getTimeStamp() {
//		return timeStamp;
//	}
//
//	public void setTimeStamp(Date timeStamp) {
//		this.timeStamp = timeStamp;
//	}
//
//	public String getMessage() {
//		return message;
//	}
//
//	public void setMessage(String message) {
//		this.message = message;
//	}
//
//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}
}
