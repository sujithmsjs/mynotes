package com.suji.blanks.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Question {
	
	private int id;
	private String question;
	private List<Answer> answers = new ArrayList<>();
	
}
