package com.j8.lambda;

public class UseRunnable {
	public static void main(String[] args) {

		Thread x1 = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("anonymous call -1");
			}
		});

		Thread x2 = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("anonymous call -2 ");
			}
		});

		Thread x3 = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("anonymous call -3");
			}
		});

		x1.start();
		x2.start();
		x3.start();
	}
}
