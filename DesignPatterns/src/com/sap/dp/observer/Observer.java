package com.sap.dp.observer;

/**
 * All weather components implements the {@link Observer} interface. This gives
 * the subject a common interface to talk to when it comes time to update the
 * observer.
 * 
 * @author kkanka
 *
 */
public interface Observer {
	public void update(float temperature, float humidity, float pressure);
}
