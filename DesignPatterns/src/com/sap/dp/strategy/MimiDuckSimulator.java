package com.sap.dp.strategy;

import com.sap.dp.strategy.client.Duck;
import com.sap.dp.strategy.client.ModelDuck;

public class MimiDuckSimulator {
	
	public static void main(String[] args) {
		
		Duck model = new ModelDuck();
		model.performFly();
		model.setFlyBehaviour(new FlyRocketPowered());
		model.performFly();
		
		model.performQuack();
	}
}
