package com.suji.spring.app2;

import org.springframework.stereotype.Component;

@Component
public class Redgear implements Joystick {

	public int totalKeys() {
		return 10;
	}

	public String getName() {
		return "Redgear Pro Wireless Gamepad";
	}

	public void play(Game game) {
		System.out.println(game.getName()+" is playing using "+getName()+"; ");
		game.up();
	}
}
