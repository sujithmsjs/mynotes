package com.suji.service;

import java.util.List;
import java.util.Stack;

import com.suji.model.Answer;
import com.suji.model.Question;


public class QuestionGenerator {
	
public static Question getQuestion(String str) {
		Question question = new Question();
		List<Answer> answers = question.getAnswers();
		
		boolean hasError = false;
		
		Stack<Character> stack = new Stack<>();
		stack.add('#');

		
		StringBuilder sb = new StringBuilder();
		StringBuilder queStr = new StringBuilder();
		Answer ans = new Answer();
		
		
		for (int i = 0; i < str.length(); i++) {

			switch (str.charAt(i)) {
			case '<':
				if (stack.peek() == '#') {
					stack.push(str.charAt(i));
				} else {
					hasError = true;
					break;
				}
				break;
			case ':':
				if (stack.peek() == '<') {
					ans.setAnswer(sb.toString());
					//System.out.println("Answer: " + ans);
					sb = new StringBuilder();
				} else {
					hasError = true;
					break;
				}
				stack.push(str.charAt(i));

				break;
			case '>':
				if (stack.peek() == '<') {
					ans.setAnswer(sb.toString());
					
					Answer a = new Answer();
					a.setAnswer(ans.getAnswer());
					a.setHint(ans.getHint());
					a.setQuestion(question);
					answers.add(a);
					//System.out.println("Answer: " + ans);
					
					ans = new Answer();
					sb = new StringBuilder();
				} else if (stack.peek() == ':') {
					ans.setHint(sb.toString());
					
					Answer a = new Answer();
					a.setAnswer(ans.getAnswer());
					a.setHint(ans.getHint());
					a.setQuestion(question);
					answers.add(a);
					
					ans = new Answer();
					//System.out.println("Answer: " + ans);
					sb = new StringBuilder();
				} else {
					hasError = true;
					break;
				}
				stack.clear();
				stack.push('#');
				break;
			default:
				if (stack.peek() == '<') {
					sb.append(str.charAt(i));
					queStr.append('_');
				}else if(stack.peek() == ':') {
					sb.append(str.charAt(i));
				}else {
					queStr.append(str.charAt(i));
				}
				break;
			}
		}
		
		question.setQuestion(queStr.toString());
		
		if(hasError) {
			return null;
		}else {
			return  question;
		}
	}
}
