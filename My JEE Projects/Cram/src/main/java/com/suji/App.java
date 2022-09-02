package com.suji;

interface I{
	int m1();
}

class B implements I{

	@Override
	public int m1() {
		System.out.println("B class...");
		return 0;
	}
	
}

class A implements I{

	@Override
	public int m1() {
		System.out.println("A class...");
		return 0;
	}
	
}

public class App {	

	public static void main(String[] args) {
		int[] n = new int[10];

		I i = new A();
		I i2 = new B();
		System.out.println(i2.getClass());
	
	}
	
}
