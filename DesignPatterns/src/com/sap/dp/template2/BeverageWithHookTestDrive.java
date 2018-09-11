package com.sap.dp.template2;

public class BeverageWithHookTestDrive {

	public static void main(String[] args) {
		
		TeaBeverageWithHook teaBeverageWithHook = new TeaBeverageWithHook();
		teaBeverageWithHook.prepareRecipe();
		
		CoffeeBeverageWithHook beverageWithHook = new CoffeeBeverageWithHook();
		beverageWithHook.prepareRecipe();
		
		
	}

}
