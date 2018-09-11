package com.sap.dp.template2;

public class TeaBeverage extends CoffeineBeverage {

	@Override
	void brew() {
		System.out.println("Steeping Tea");
	}

	@Override
	void addCondiments() {
		System.out.println("Adding Lemon");
	}

}
