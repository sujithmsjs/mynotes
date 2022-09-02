package com.suji.spring.app2;

import org.springframework.stereotype.Component;

@Component
public class PacMan implements Game {

	public void up() {
		System.out.println("Turned upwards...");
	}

	public String getName() {
		return "Pac-Man";
	}



}
