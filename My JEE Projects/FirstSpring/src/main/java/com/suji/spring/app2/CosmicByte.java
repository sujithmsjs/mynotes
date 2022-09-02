package com.suji.spring.app2;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class CosmicByte implements Joystick {

	public int totalKeys() {
		return 16;
	}

	public String getName() {
		return "Cosmic Byte C3070W Nebula";
	}

	public void play(Game game) {
		System.out.println(game.getName()+" is playing using "+getName());
		game.up();
	}
}
