package com.suji.spring.app2;

import org.springframework.stereotype.Component;

public interface Joystick {
	public int totalKeys();
	public String getName();
	public void play(Game game);
}
