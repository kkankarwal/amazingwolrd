package com.sap.dp.singleton;

public class BasicSingleton {

	private static BasicSingleton instance = null;
	
	private BasicSingleton() {
		System.out.println("I'm instanciating a Basic Singleton object ... !");
	}

	public static BasicSingleton getSingleton() {
		
		if (null == instance)
			instance = new BasicSingleton();

		return instance;
	}
}
