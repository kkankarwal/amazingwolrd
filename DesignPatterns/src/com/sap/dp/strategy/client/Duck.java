package com.sap.dp.strategy.client;

import com.sap.dp.strategy.FlyBehaviour;
import com.sap.dp.strategy.QuackBehaviour;

public abstract class Duck {

	private FlyBehaviour flyBehaviour;
	private QuackBehaviour quackBehaviour;

	public Duck() {
		// this.flyBehaviour = new FlyBehaviour();
		// this.quackBehaviour = new QuackBehaviour();
	}

	public abstract void display();

	public void swim() {
	}

	public void performQuack() {
		quackBehaviour.quack();
	}

	public void performFly() {
		flyBehaviour.fly();
	}

	public FlyBehaviour getFlyBehaviour() {
		return flyBehaviour;
	}

	public void setFlyBehaviour(FlyBehaviour flyBehaviour) {
		this.flyBehaviour = flyBehaviour;
	}

	public QuackBehaviour getQuackBehaviour() {
		return quackBehaviour;
	}

	public void setQuackBehaviour(QuackBehaviour quackBehaviour) {
		this.quackBehaviour = quackBehaviour;
	}

}
