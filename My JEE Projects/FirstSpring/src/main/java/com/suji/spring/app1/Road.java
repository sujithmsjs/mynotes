package com.suji.spring.app1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Road {
	
	@Autowired
	@Qualifier("car")
	private Vehicle vehicle;

	public void showVehicle() {
		System.out.println(vehicle);
	}
	
	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
}
