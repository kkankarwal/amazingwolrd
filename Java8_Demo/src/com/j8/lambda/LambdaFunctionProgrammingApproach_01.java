package com.j8.lambda;

public class LambdaFunctionProgrammingApproach_01 {

	public static void main(String[] args) {

		// Style-1
		MyRunnable myRunnable = new MyRunnable();
		Thread myThread = new Thread(myRunnable);
		myThread.start();

		// Style-2
		Thread myThread2 = new Thread(new MyRunnable());
		myThread2.start();

		// Style-3
		new Thread(new MyRunnable()).start();

		// Style-4
		new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("anonymous call");
			}
		}).start();

		// Lambda Style
		Runnable r = () -> System.out.println("Lambda Style");
		new Thread(r).start();

		// Lambda Style
		new Thread(() -> {
			System.out.println("enhanced Lanbda style");
		}).start();
	}

}

// case :1
class MyRunnable implements Runnable {

	@Override
	public void run() {
		System.out.println("hello world");
	}
}
