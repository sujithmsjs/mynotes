package com.suji.mod;

public class Promise {
	
	private int vno;
	private String verse;
	private String ref;
	
	
	public Promise(int vno, String verse, String ref) {
		super();
		this.vno = vno;
		this.verse = verse;
		this.ref = ref;
	}
	
	public int getVno() {
		return vno;
	}
	public void setVno(int vno) {
		this.vno = vno;
	}
	
	public String getVerse() {
		return verse;
	}
	public void setVerse(String verse) {
		this.verse = verse;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
}
