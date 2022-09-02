package com.suji.acc;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
	// @GeneratedValue(strategy = GenerationType.AUTO)

	@Id
	@GeneratedValue
	private int id;
	private String name;
	@OneToMany(cascade = CascadeType.ALL, 
			fetch = FetchType.EAGER,
			mappedBy = "user",
			orphanRemoval = true)
	private List<Acc> acc = new ArrayList<Acc>();


	public User() {
	}

	public User(String name, List<Acc> acc) {
		this.name = name;
		this.acc = acc;
	}

	public User(String name) {
		super();
		this.name = name;
	}

	public List<Acc> getAcc() {
		return acc;
	}
	public void setAcc(List<Acc> acc) {
		this.acc = acc;
	}
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

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", acc=" + acc + "]";
	}
}
