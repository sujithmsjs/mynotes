package com.suji.spring.apps;

import java.util.Arrays;
import java.util.Iterator;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.suji.spring.app1.Bike;
import com.suji.spring.app1.Car;
import com.suji.spring.app1.Road;
import com.suji.spring.app1.Vehicle;
import com.suji.spring.config.AppConfig;
import com.suji.spring.config.BeanConfig;

public class App {
	public static void main1(String[] args) {
		// ApplicationContext context = new
		// ClassPathXmlApplicationContext("spring.xml");
		// ApplicationContext context = new
		// AnnotationConfigApplicationContext(BeanConfig.class);
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		System.out.println(Arrays.asList(context.getBeanDefinitionNames()));
		System.out.println("==================");
	 	Road road =  (Road) context.getBean("road");
		Vehicle vehicle = road.getVehicle();
		vehicle.drive();
		Car car = (Car) context.getBean("car");
		System.out.println(vehicle.getClass());
		System.out.println(vehicle==car);
	}
}
