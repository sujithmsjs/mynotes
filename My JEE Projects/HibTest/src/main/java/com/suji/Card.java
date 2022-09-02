package com.suji;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Card {
	@Id
	private int cid;
	private String question;
	
	@OneToMany
	private List<Ans> ans = new ArrayList<Ans>();

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<Ans> getAns() {
		return ans;
	}

	public void setAns(List<Ans> ans) {
		this.ans = ans;
	}

	@Override
	public String toString() {
		return "Card [cid=" + cid + ", question=" + question + ", ans=" + ans + "]";
	}
	
	
}
