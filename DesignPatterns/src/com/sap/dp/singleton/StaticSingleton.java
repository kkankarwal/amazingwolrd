package com.sap.dp.singleton;

public class StaticSingleton {

	private static StaticSingleton INSTANCE = new StaticSingleton();

	private StaticSingleton() {
		System.out.println("I'm instanciating a Static Singleton object ... !");
	}

	public static StaticSingleton getSingleton() {

		return INSTANCE;
	}
}
