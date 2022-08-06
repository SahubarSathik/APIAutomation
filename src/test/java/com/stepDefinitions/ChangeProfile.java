package com.stepDefinitions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import com.base.BaseClass;
import com.endpoints.Endpoints;
import com.pojo.Login_Output_Pojo;
import com.pojo.UpdateProfilePic_Output_pojo;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class ChangeProfile extends BaseClass{
	
	


	@Given("User add the headers and multiPart and beares authorize")
	public void user_add_the_headers_and_multiPart_and_beares_authorize() {
		List<Header> h = new ArrayList<Header>();
		Header h1 = new Header("Content-Type", "multipart/form-data");

		Header h2 = new Header("Authorization", "Bearer " +LoginStep.commonVariables.logtoken);

		h.add(h1);
		h.add(h2);

		Headers headers = new Headers(h);
		addHeaders(headers);
	}

	@Given("User add formData and Upload image file")
	public void user_add_formData_and_Upload_image_file() {
		formData("C:\\Users\\Sathik\\Downloads\\cat.jpeg");
	}

	

	@Then("User add request type as {string} for ChangeProPic")
	public void user_add_request_type_as_for_ChangeProPic(String string) {
		response = requestType("POST", Endpoints.UPDATEPROFILEPIC);
	}

	
	@Then("User verify the profile updated message as {string}")
	public void user_verify_the_profile_updated_message_as(String string) {
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		LoginStep.commonVariables.setStatusCode(statusCode);
		UpdateProfilePic_Output_pojo updateProfilePic_Output_pojo = response.as(UpdateProfilePic_Output_pojo.class);
		String message = updateProfilePic_Output_pojo.getMessage();
		Assert.assertEquals(message, string, "Verify PrifilePicture Updates?");
	}

}
