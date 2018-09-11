package com.sap.dp.template2;

public class CoffeeBeverage extends CoffeineBeverage {

	@Override
	void brew() {
		System.out.println("Bripping Coffee though filter");
	}

	@Override
	void addCondiments() {
		System.out.println("Adding Sugar and Milk");
	}

}
