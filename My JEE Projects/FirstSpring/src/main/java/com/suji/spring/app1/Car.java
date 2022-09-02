package com.suji.spring.app1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class Car implements Vehicle {

	private String name;
	
	@Autowired
	private Tyre tyre;
	
	
	public Tyre getTyre() {
		return tyre;
	}

	public void setTyre(Tyre tyre) {
		this.tyre = tyre;
	}

	public Car(String name) {
		super();
		System.out.println("Car constructor");
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Car() {
		System.out.println("Car Constructor");
	}
	
	public void drive() {
		System.out.println(getWheels()+" Wheels Car Moving");
		tyre.tyreDesc();
	}

	public int getWheels() {
		return 4;
	}

}
