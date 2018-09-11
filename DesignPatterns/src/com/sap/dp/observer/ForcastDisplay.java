package com.sap.dp.observer;

public class ForcastDisplay implements Observer, DisplayElements {

	private float temperature;
	private float humidity;
	private float pressure;
	private Subject weatherData;

	public ForcastDisplay(Subject weatherData) {
		this.weatherData = weatherData;
		weatherData.registerObserver(this);
	}

	@Override
	public void update(float temperature, float humidity, float pressure) {
		this.temperature = temperature;
		this.humidity = humidity;
		display();
	}

	@Override
	public void display() {
		System.out.println("Forecast conditions : " + temperature
				+ "F degrees and " + humidity + "% humidity");
	}

}
