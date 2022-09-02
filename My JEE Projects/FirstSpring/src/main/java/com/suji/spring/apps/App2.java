package com.suji.spring.apps;

import java.util.Arrays;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.suji.spring.app2.GameRunner;
import com.suji.spring.config.App2Config;

public class App2 {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(App2Config.class);
		int count = context.getBeanDefinitionCount();
		String[] names = context.getBeanDefinitionNames();
		System.out.println(Arrays.asList(names));
		System.out.println("Count: "+count);
		
		System.out.println("===============");
		
		GameRunner gameRunner = (GameRunner) context.getBean("gameRunner");
		gameRunner.play();
	}
}
