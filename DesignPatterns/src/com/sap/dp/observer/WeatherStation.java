package com.sap.dp.observer;

public class WeatherStation {
	public static void main(String[] args) {
		
		WeatherData weatherData = new WeatherData();

		CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay(
				weatherData);
		StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
		ForcastDisplay forcastDisplay = new ForcastDisplay(weatherData);
		
		weatherData.setMeasurements(80, 65, 30.0f);
	}
}
