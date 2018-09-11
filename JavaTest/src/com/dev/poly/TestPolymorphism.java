package com.dev.poly;

public class TestPolymorphism {

	public static void main(String[] args) {
		method(null);
	}

	public static void method(Object o) {
		System.out.println("Object impl");
	}

	public static void method(String s) {
		System.out.println("String impl");
	}

//	public static void method(StringBuffer i) {
//		System.out.println("StringBuffer impl");
//	}

}
