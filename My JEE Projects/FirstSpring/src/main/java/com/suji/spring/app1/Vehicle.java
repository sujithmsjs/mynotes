package com.suji.spring.app1;

import org.springframework.stereotype.Component;

@Component
public interface Vehicle {
	public void drive();
	public int getWheels();
}
