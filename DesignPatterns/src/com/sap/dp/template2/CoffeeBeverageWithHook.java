package com.sap.dp.template2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CoffeeBeverageWithHook extends CoffeineBeverageWithHook {

	@Override
	void brew() {
		System.out.println("Bripping Coffee though filter");
	}

	@Override
	void addCondiments() {
		System.out.println("Adding Sugar and Milk");
	}

	@Override
	public boolean customerWantsCondiments() {
		String answer = getUserInput();

		if (answer.toLowerCase().startsWith("y")) {
			return true;
		} else {
			return false;
		}
	}

	private String getUserInput() {
		String answer = null;

		System.out
				.println("Would you like milk and sugar with your coffee (y/n) ?");

		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(System.in));

		try {
			answer = bufferedReader.readLine();
		} catch (IOException ex) {
			System.err.println("IO Error trying to read your answer");
		}

		if (answer == null)
			return "no";

		return answer;
	}
}
