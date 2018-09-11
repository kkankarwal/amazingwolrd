package com.sap.dp.template2;

public abstract class CoffeineBeverageWithHook {

	/**
	 * Its declared final because we don't want our subclasses to be able to
	 * override this method. It serves as template for an algorithm. In this
	 * case, an algorithm for making caffeinated beverage.
	 * 
	 * <b>Hence prepareRecipe() method becomes our template method. </b>
	 * 
	 * A template method defines the steps of an algorithm and allows subclasses
	 * to provide the implementation for one or more steps.
	 */
	final void prepareRecipe() {
		boilWater();
		brew();
		pourInCup();
		if (customerWantsCondiments())
		addCondiments();
	}

	public boolean customerWantsCondiments() {
		return true;
	}

	abstract void brew();

	abstract void addCondiments();

	public void boilWater() {
		System.out.println("Boiling water");
	}

	public void pourInCup() {
		System.out.println("Pouring into cup");
	}

}
