package com.suji.mod;

public class User {
	private int uid;
	String fname, uname, pwd;
	
	
	
	public User(String fname, String uname, String pwd) {
		
		this.fname = fname;
		this.uname = uname;
		this.pwd = pwd;
		
	}
	
	
	public int getUid() {
		return uid;
	}
	
	public String getPasswrod() {
		return pwd;
	}
	
	
	
	
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}


	@Override
	public String toString() {
		return "User [uid=" + uid + ", fname=" + fname + ", uname=" + uname + "]";
	}
	
	
	
}
