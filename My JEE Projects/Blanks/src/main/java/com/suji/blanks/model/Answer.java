package com.suji.blanks.model;

import lombok.Data;

@Data
public class Answer {
	
	private int id;
	private String answer;
	private String hint;
	
	
	
	public Answer(String answer, String hint) {
		super();
		this.answer = answer;
		this.hint = hint;
	}



	public Answer() {
		super();
	}
	
	
}
