package com.sap.dp.template;

public abstract class Beverage {
	
	public abstract void prepareRecipe();
	
	public void boilWater() {
		System.out.println(" Boiling water");
	}
	
	public void pourInCup() {
		System.out.println("Pouring into cup");
	}

}
