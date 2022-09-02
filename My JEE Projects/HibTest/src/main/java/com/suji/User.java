package com.suji;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	private int uid;
	private String name;
	private double height;
	private int marks;
	private Date dob;
	

	
	
	public User() {
		super();
	}


	public User(int uid, String name, double height) {
		super();
		this.uid = uid;
		this.name = name;
		this.height = height;
	}
	
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}


	@Override
	public String toString() {
		return "User [uid=" + uid + ", name=" + name + ", height=" + height + ", marks=" + marks + ", dob=" + dob + "]";
	}

}
