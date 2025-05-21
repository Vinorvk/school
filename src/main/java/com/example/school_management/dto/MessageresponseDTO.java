package com.example.school_management.dto;

import lombok.Data;

import java.util.Date;

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
}