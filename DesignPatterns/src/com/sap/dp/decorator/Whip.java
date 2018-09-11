package com.sap.dp.decorator;

public class Whip extends CondimentDecorator {

	Beverage beverage;

	public Whip(Beverage beverage) {
		this.beverage = beverage;
	}

	@Override
	public String getDescription() {
		return beverage.getDescription() + ", Whip";
	}

	public double cost() {
		return beverage.cost() + 0.15d;
	}

}
