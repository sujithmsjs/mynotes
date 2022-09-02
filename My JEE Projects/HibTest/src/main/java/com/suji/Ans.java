package com.suji;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Ans {
	@Id
	private int aid;
	private String answer;
	private String hint;
	
	
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getHint() {
		return hint;
	}
	public void setHint(String hint) {
		this.hint = hint;
	}
	@Override
	public String toString() {
		return "Ans [aid=" + aid + ", answer=" + answer + ", hint=" + hint + "]";
	}
	
	
	
}
