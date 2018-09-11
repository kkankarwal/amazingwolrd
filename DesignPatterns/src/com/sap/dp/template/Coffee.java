package com.sap.dp.template;

public class Coffee extends Beverage {

	public void prepareRecipe() {
		boilWater();
		brewCoffeeGrinds();
		pourInCup();
		addSugarAndMilk();
	}

	public void brewCoffeeGrinds() {
		System.out.println("Bripping Coffee though filter");
	}

	public void addSugarAndMilk() {
		System.out.println("Adding Sugar and Milk ... ");
	}
}
