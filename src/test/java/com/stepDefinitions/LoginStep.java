package com.stepDefinitions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import com.base.BaseClass;
import com.endpoints.Endpoints;
import com.pojo.AddAddress_Input_Pojo;
import com.pojo.AddAddress_Output_Pojo;
import com.pojo.CommonVariables;
import com.pojo.Login_Output_Pojo;
import com.pojo.UpdateAddress_Input_pojo;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class LoginStep extends BaseClass {
	
	public static String logtoken;
	public static int address_id;
	public static CommonVariables commonVariables;
	Login_Output_Pojo login_Output_Pojo;
	public static  int statusCode;
	
	
	
	@Given("User add header")
	public void user_add_header() {
		addHeader("Content-Type", "application/json");
	}

	@Given("User add basic authentication for login")
	public void user_add_basic_authentication_for_login() throws FileNotFoundException, IOException {
		basicAuth(getPropertyValue("username"), getPropertyValue("password"));
	}

	@When("User send {string} request for login endpoint")
	public void user_send_request_for_login_endpoint(String POST) {
		 response = requestType(POST, Endpoints.LOGIN);
		 login_Output_Pojo = response.as(Login_Output_Pojo.class);
		 int statusCode = getStatusCode(response);
		 
		  commonVariables = new CommonVariables();
		  CommonVariables.setStatusCode(statusCode);
	}

	@Then("User verify the response body firstname present as {string} and get the logtoken saved")
	public void user_verify_the_response_body_firstname_present_as_and_get_the_logtoken_saved(String string) {
		String first_name = login_Output_Pojo.getData().getFirst_name();
		System.out.println(first_name);
		Assert.assertEquals(first_name, string, "Verify FirstName");
		logtoken = login_Output_Pojo.getData().getLogtoken();
		CommonVariables.setLogtoken(logtoken);

	}


}
