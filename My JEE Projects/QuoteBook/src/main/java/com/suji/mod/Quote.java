package com.suji.mod;

public class Quote {
	private int qid,uid;
	private String quote, authar;
	
	
	
	
	public Quote(int qid, int uid, String quote, String authar) {
		super();
		this.qid = qid;
		this.uid = uid;
		this.quote = quote;
		this.authar = authar;
	}


	@Override
	public String toString() {
		return "Quote [qid=" + qid + ", uid=" + uid + ", quote=" + quote + ", authar=" + authar + "]";
	}


	public int getQid() {
		return qid;
	}


	public void setQid(int qid) {
		this.qid = qid;
	}


	public int getUid() {
		return uid;
	}


	public void setUid(int uid) {
		this.uid = uid;
	}


	public String getQuote() {
		return quote;
	}


	public void setQuote(String quote) {
		this.quote = quote;
	}


	public String getAuthar() {
		return authar;
	}


	public void setAuthar(String authar) {
		this.authar = authar;
	}
	
	
	
	
	
}
