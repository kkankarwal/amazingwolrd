package com.sap.dp.strategy.client;

import com.sap.dp.strategy.FlyNoWay;
import com.sap.dp.strategy.Quack;

public class ModelDuck extends Duck {

	public ModelDuck() {
		setFlyBehaviour(new FlyNoWay());
		setQuackBehaviour(new Quack());
	}

	@Override
	public void display() {
		System.out.println("I'm a model duck");
	}

}
