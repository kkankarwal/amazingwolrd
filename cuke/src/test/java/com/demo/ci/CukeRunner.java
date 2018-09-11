package com.demo.ci;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
// @CucumberOptions(plugin = { "pretty", "html:target/cucumber" })
/*
 * @CucumberOptions( features = "classpath:features", plugin = {"pretty",
 * "html:target/cucumber-html-report"}, tags = {} )
 */
@CucumberOptions(
		dryRun = false, 
		strict = true, 
		features = "features/seleniumframework.feature:9", 
		glue = "step_definitions", 
		tags = {"~@wip", "@beta2" }, 
		monochrome = false, 
		plugin = { "pretty", "html:target/cucumber",
				"json:results/cucumber.json",
				"junit:results/cucumber.xml" }
		)
public class CukeRunner {

}
