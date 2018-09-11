package com.sap.dp.template;


public class Tea extends Beverage {

	public void prepareRecipe() {
		boilWater();
		steepTeaBag();
		addLemon();
		pourInCup();
	}

	public void steepTeaBag() {
		System.out.println("Steeping the tea");
	}

	public void addLemon() {
		System.out.println("Adding Lemon ");
	}

}
