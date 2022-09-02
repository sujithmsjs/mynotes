package com.suji.spring.app2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class GameRunner {
	@Autowired
	@Qualifier(value = "pacMan")
	private Game game;
	@Autowired
	private Joystick joystick;
	
	public void play() {
		joystick.play(game);
	}
	
}
