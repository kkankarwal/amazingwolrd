package com.j8.lambda;

public class UseSimpleInterface {
	public static void main(String[] args) {

		SimpleInterface x = () -> System.out.println("I dont know what to do ");
		x.doSomething();
	}
}
