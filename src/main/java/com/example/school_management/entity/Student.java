package com.example.school_management.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="student")
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	
	private String id;
	@Column(name ="name")
	private String name;
	@Column(name ="school_name")
	private String school_name;
	
	@ManyToOne
	private School school;
	}
