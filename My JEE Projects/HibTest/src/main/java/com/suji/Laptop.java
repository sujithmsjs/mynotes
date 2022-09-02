package com.suji;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Laptop {
	
	@Id
	private int lapId;
	private	String company;
	private	String processor;
	@OneToOne
	private Student student;
	
	

	public int getLapId() {
		return lapId;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public void setLapId(int lapId) {
		this.lapId = lapId;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}


	public String getProcessor() {
		return processor;
	}

	public void setProcessor(String processor) {
		this.processor = processor;
	}

	@Override
	public String toString() {
		return "Laptop [lapId=" + lapId + ", company=" + company + ", processor=" + processor + "]";
	}
}
