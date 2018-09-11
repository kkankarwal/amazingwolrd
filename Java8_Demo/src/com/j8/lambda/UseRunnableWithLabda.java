package com.j8.lambda;

public class UseRunnableWithLabda {

	public static void main(String[] args) {

		// With Lamda thread execution order is certain

		// Lambda Style
		Runnable r1 = () -> System.out.println("Lambda Style - 1");

		// Lambda Style
		Runnable r2 = () -> System.out.println("Lambda Style - 2");

		// Lambda Style
		Runnable r3 = () -> System.out.println("Lambda Style - 3 ");
	
		
		new Thread(r1).start();
		new Thread(r2).start();
		new Thread(r3).start();
	}
}
