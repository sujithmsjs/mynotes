package com.suji.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private int marks;
	
	public Student() {
	}
	
	public Student(String name, int marks) {
		super();
		this.name = name;
		this.marks = marks;
	}
}
