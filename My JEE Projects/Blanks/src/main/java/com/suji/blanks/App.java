package com.suji.blanks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.util.function.Consumer;

import javax.swing.plaf.nimbus.State;

import com.suji.blanks.model.Answer;
import com.suji.blanks.model.Question;

public class App {

	public static void main(String[] args) {

		Question question = getQuestion("<This:?> is <mangostarts: with m>, it the most <dilicious: tasty> fruit");
		System.out.println(question);
	
	}

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
					answers.add(a);
					//System.out.println("Answer: " + ans);
					
					sb = new StringBuilder();
				} else if (stack.peek() == ':') {
					ans.setHint(sb.toString());
					
					Answer a = new Answer();
					a.setAnswer(ans.getAnswer());
					a.setHint(ans.getHint());
					answers.add(a);
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
