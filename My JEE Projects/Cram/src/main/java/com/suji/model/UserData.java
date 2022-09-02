package com.suji.model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.TrueFalseType;

import lombok.Data;

@Data
@Entity
@Table(name = "demo1")
public class UserData {
	@Id
	@Column(name = "user_id", length = 5)
	//@GeneratedValue
	private int uid;
	private String name;
	@Column(name = "username",length = 20,unique = true, nullable = false)
	private String username;
	@Column(nullable = false, length = 10)
	private String password;
	
	@Temporal(TemporalType.DATE)
	private Calendar dob;
	
	@Temporal(TemporalType.TIME)
	@Column(name="birth_time")
	private Date birthtime;

	private double cgpa;
	private float percentage;
	private byte section;
	private int marks;
	
	@Column(name = "has_ncc", nullable = false)
	private boolean hasNCC;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private Calendar createdOn;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_date")
	private Date modifyDate;

	
	
	public UserData() {
	}
	
	public UserData(int uid ,String name, String username) {
		uid = uid;
		name = name;
		username = username;
	}
	
	public UserData(int uid ,String name, String username, String password) {
		uid = uid;
		name = name;
		username = username;
		password = password;
	}

	public UserData(int uid ,String name, String username, String password, double cgpa, int marks, boolean hasNCC) {
		uid = uid;
		name = name;
		username = username;
		password = password;
		cgpa = cgpa;
		marks = marks;
		hasNCC = hasNCC;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		password = password;
	}

	public double getCgpa() {
		return cgpa;
	}

	public void setCgpa(double cgpa) {
		cgpa = cgpa;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		marks = marks;
	}

	public boolean isHasNCC() {
		return hasNCC;
	}

	public void setHasNCC(boolean hasNCC) {
		hasNCC = hasNCC;
	}



	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		modifyDate = modifyDate;
	}

	public Calendar getDob() {
		return dob;
	}

	public void setDob(Calendar dob) {
		dob = dob;
	}

	public Date getBirthtime() {
		return birthtime;
	}

	public void setBirthtime(Date birthtime) {
		birthtime = birthtime;
	}

	public float getPercentage() {
		return percentage;
	}

	public void setPercentage(float percentage) {
		percentage = percentage;
	}

	public byte getSection() {
		return section;
	}

	public void setSection(byte section) {
		section = section;
	}

	public Calendar getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Calendar createdOn) {
		createdOn = createdOn;
	}

	@Override
	public String toString() {
		return "UserData [uid=" + uid + ", name=" + name + ", username=" + username + ", password=" + password + "]";
	}


	
	


	
	
	
}
