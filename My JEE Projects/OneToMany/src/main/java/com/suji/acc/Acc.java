package com.suji.acc;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Acc {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	@ManyToOne
	private User user;
	
	
	//Constructors.
	public Acc() {}
	public Acc(String name) {
		this.name = name;
	}
	public Acc(String name,User user) {
		this.name = name;
		this.user = user;
	}
	
	//Setters and Getters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User users) {
		this.user = users;
	}
	
	@Override
	public String toString() {
		return "Acc [id=" + id + ", name=" + name + "]";
	}
}
