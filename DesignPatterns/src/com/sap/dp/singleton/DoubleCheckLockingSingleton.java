package com.sap.dp.singleton;

public class DoubleCheckLockingSingleton {

	private static DoubleCheckLockingSingleton instance = null;

	private DoubleCheckLockingSingleton() {
		System.out.println("I'm instanciating a DoubleCheckLockingSingleton object ... !");
	}

	public static DoubleCheckLockingSingleton getSingleton() {
		if (null == instance) {
			synchronized (DoubleCheckLockingSingleton.class) {

				if (null == instance)
					instance = new DoubleCheckLockingSingleton();
			}
		}
		return instance;
	}
}
