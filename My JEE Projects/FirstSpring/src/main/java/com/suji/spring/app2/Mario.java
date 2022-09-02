package com.suji.spring.app2;

import org.springframework.stereotype.Component;

@Component
public class Mario implements Game {

	public String getName() {
		return "Super Mario";
	}

	public void up() {
		System.out.println("Jumped");
	}



}
