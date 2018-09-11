package com.sap.dp.observer;

public interface Subject {

	public void registerObserver(Observer observer);

	public void removeObserver(Observer observer);

	/**
	 * This method is called to notify all observers when the subject's state
	 * has changed.
	 */
	public void notifyObservers();
}
