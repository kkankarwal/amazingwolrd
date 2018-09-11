package com.demo.ci.step;

import java.util.List;

import cucumber.api.java.en.Given;

public class MyStepDefinition {

	@Given("the following animals:")
	public void the_following_animals(List<String> animals) {
	}

	@Given("I have (\\d+) cukes in my belly")
	public void I_have_cukes_in_my_belly(int cukes) {
		System.out.format("Cukes: %n\n", cukes);
	}
}
