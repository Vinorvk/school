package com.example.school_management.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.example.school_management.dto.MessageresponseDTO;

@ControllerAdvice
public class GlobalException {

	    @ExceptionHandler({UserNotFoundException.class})
	    @ResponseStatus(HttpStatus.NOT_FOUND)
	    public ResponseEntity<MessageresponseDTO> handleStudentNotFoundException(UserNotFoundException exception,WebRequest request) {
	    	MessageresponseDTO messageresponse = new MessageresponseDTO(
	    			HttpStatus.BAD_REQUEST.value(),
	    			new Date(),
	    			exception.getMessage(),
	    			request.getDescription(false));
	    	return new ResponseEntity<>(messageresponse,HttpStatus.BAD_REQUEST);
	    }
	}
