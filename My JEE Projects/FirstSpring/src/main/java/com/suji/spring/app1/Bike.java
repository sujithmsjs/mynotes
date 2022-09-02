package com.suji.spring.app1;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("bike")
@Primary
public class Bike implements Vehicle {

	
	public Bike() {
		System.out.println("Bike Constructor");
	}
	
	public void drive() {
		System.out.println(getWheels()+" Wheels Bike Moving");
	}

	public int getWheels() {
		return 2;
	}

}
