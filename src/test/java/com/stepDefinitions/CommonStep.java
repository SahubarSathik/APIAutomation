package com.stepDefinitions;
import org.testng.Assert;

import com.base.BaseClass;

import cucumber.api.java.en.Then;


public class CommonStep extends BaseClass {
	public static int statusCode;

	

	@Then("User verify the status code {int}")
	public void user_verify_the_status_code(int int1) {
		statusCode = LoginStep.commonVariables.getStatusCode();
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, int1, "Verify Status Code");
	}

}
