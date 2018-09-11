package com.sap.dp.singleton;

public class DemoSingleton {
	public static void main(String[] args) {
		
		BasicSingleton singleton = BasicSingleton.getSingleton();
		BasicSingleton singleton2 = BasicSingleton.getSingleton();
		
		StaticSingleton singleton3 = StaticSingleton.getSingleton();
		StaticSingleton singleton4 = StaticSingleton.getSingleton();
		
		DoubleCheckLockingSingleton singleton5 = DoubleCheckLockingSingleton.getSingleton();
		DoubleCheckLockingSingleton singleton6 = DoubleCheckLockingSingleton.getSingleton();
		
	}
}
