package com.suji.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.suji.spring.app1.Bike;
import com.suji.spring.app1.Car;
import com.suji.spring.app1.Tyre;
import com.suji.spring.app1.Vehicle;

@Configuration
public class BeanConfig {
	
	public Vehicle getVehicle() {
		return getCar();
	}

	@Bean
	public Car getCar() {
		return new Car("Tesla");
	}
	
	@Bean
	public Tyre getTyre() {
		return new Tyre();
	}
	
	@Bean
	public Bike getBike() {
		return new Bike();
	}
}
