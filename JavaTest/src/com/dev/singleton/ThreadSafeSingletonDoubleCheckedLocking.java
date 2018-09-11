package com.dev.singleton;

public class ThreadSafeSingletonDoubleCheckedLocking {

	private static ThreadSafeSingletonDoubleCheckedLocking instance;

	private ThreadSafeSingletonDoubleCheckedLocking() {
	}

	public static ThreadSafeSingletonDoubleCheckedLocking getInstanceUsingDoubleLocking() {
		if (instance == null) {
			synchronized (ThreadSafeSingletonDoubleCheckedLocking.class) {
				if (instance == null) {
					instance = new ThreadSafeSingletonDoubleCheckedLocking();
				}
			}
		}
		return instance;
	}

}