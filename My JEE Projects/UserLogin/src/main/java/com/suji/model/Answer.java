package com.suji.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ManyToAny;

import lombok.Data;

@Data
@Entity
public class Answer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String answer;
	private String hint;
	@ManyToOne(cascade = CascadeType.ALL)
	private Question question;
	
	public Answer(String answer, String hint) {
		super();
		this.answer = answer;
		this.hint = hint;
	}



	public Answer() {
		super();
	}



	@Override
	public String toString() {
		return "Answer [answer=" + answer + ", hint=" + hint + "]";
	}
	
	
	
}
