package com.runner;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src\\test\\resources\\Feature_Files" }, glue = {
		"com.stepDefinitions" }, monochrome = true, dryRun = false, strict = true, plugin = { "pretty", })
public class RunnerClass {
	@AfterClass
	public static void afterClass() {

	}

}
