package com.stepDefinitions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import com.base.BaseClass;
import com.endpoints.Endpoints;
import com.payloads.Payload;
import com.pojo.AddAddress_Input_Pojo;
import com.pojo.AddAddress_Output_Pojo;
import com.pojo.CommonVariables;
import com.pojo.DeleteAddress_Input_pojo;
import com.pojo.DeleteAddress_Output_pojo;
import com.pojo.GetAllAddress_Output_pojo;
import com.pojo.Login_Output_Pojo;
import com.pojo.UpdateAddress_Input_pojo;
import com.pojo.UpdateAddress_Output_pojo;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class AddressStep extends BaseClass {

	public static int address_id;
	Login_Output_Pojo login_Output_Pojo;
	

	@Given("User add the headers and beares authorize")
	public void user_add_the_headers_and_beares_authorize() {
		// 1.header
		List<Header> h = new ArrayList<Header>();
		Header h1 = new Header("Content-Type", "application/json");
		LoginStep.commonVariables.getLogtoken();
		Header h2 = new Header("Authorization", "Bearer " + LoginStep.commonVariables.getLogtoken());
		h.add(h1);
		h.add(h2);
		Headers headers = new Headers(h);
		addHeaders(headers);
	}

	@When("User set the {string} Request Type for addUserAddress")
	public void user_set_the_Request_Type_for_addUserAddress(String string) {
		response = requestType(string, Endpoints.ADDADDRESS);
	}

	@When("User add the {string}, {string}, {string}, {string}, {int}, {int}, {int}, {string}, {string} and {string}")
	public void user_add_the_and(String first_name, String last_name, String mobile, String apartment, int state, int city,
			int country, String zipcode, String address, String address_type) {
		AddAddress_Input_Pojo addAddress_Input_Pojo = Payload.creteAddress(
				first_name, last_name, mobile, apartment, state, city, country, zipcode, address, address_type);
		addBody(addAddress_Input_Pojo);
	}

	
	
	@Then("User verify the Address added message {string}")
	public void user_verify_the_Address_added_message(String string) {
		int statusCode = response.getStatusCode();

		LoginStep.commonVariables.setStatusCode(statusCode);
		AddAddress_Output_Pojo addAddress_Output_Pojo = response.as(AddAddress_Output_Pojo.class);
		String message = addAddress_Output_Pojo.getMessage();
		address_id = addAddress_Output_Pojo.getAddress_id();
		LoginStep.commonVariables.setAddress_id(address_id);
		
		
		Assert.assertEquals(message, string, "Verify Address Created");
	}

	@When("User set the {string} Request Type for updateUserAddress")
	public void user_set_the_Request_Type_for_updateUserAddress(String POST) {
		response = requestType(POST, Endpoints.UPDATEADDRESS);
	}

	@When("User update the {int} {string}, {string}, {string}, {string}, {int}, {int}, {int}, {string}, {string} and {string}")
	public void user_update_the_and(int address_id, String first_name, String last_name, String mobile, String apartment, int state, int city,
			int country, String zipcode, String address, String address_type) {
		UpdateAddress_Input_pojo updateAddress_Input_pojo = Payload.updateAddress(address_id, first_name, last_name, mobile, apartment, state, city, country, zipcode, address, address_type);
		
		addBody(updateAddress_Input_pojo);
	}
	
	@Then("User verify the Address updated message {string}")
	public void user_verify_the_Address_updated_message(String string) {
		int statusCode = getStatusCode(response);

		LoginStep.commonVariables.setStatusCode(statusCode);
		UpdateAddress_Output_pojo updateAddress_Output_pojo = response.as(UpdateAddress_Output_pojo.class);
		String message = updateAddress_Output_pojo.getMessage();
		Assert.assertEquals(message, string, "Verify Address Updated");
	}
	
	
	

	@When("User set the {string} Request Type for getUserAddress")
	public void user_set_the_Request_Type_for_getUserAddress(String string) {
		response = requestType(string, Endpoints.GETALLADDRESSES);

	}

	
	@Then("user verify the get all address message {string}")
	public void user_verify_the_get_all_address_message(String string) {
		int statusCode = response.getStatusCode();

		LoginStep.commonVariables.setStatusCode(statusCode);

		GetAllAddress_Output_pojo getAllAddress_Output_pojo = response.as(GetAllAddress_Output_pojo.class);
		String message = getAllAddress_Output_pojo.getMessage();
		Assert.assertEquals(message, string, "Verify OK");
	}

	@When("User set the {string} Request Type for deleteAddress")
	public void user_set_the_Request_Type_for_deleteAddress(String string) {
		response = requestType(string, Endpoints.DELETEADDRESS);
	}

	@When("User add the address_id to delete")
	public void user_add_the_address_id_to_delete() {

	}

	@When("User add the address_id {int} to delete")
	public void user_add_the_address_id_to_delete(Integer int1) {
		DeleteAddress_Input_pojo deleteAddress_Input_pojo = Payload.deleteAddress(address_id);
		addBody(deleteAddress_Input_pojo);
	}

	
	@When("User verify the delete address message {string}")
	public void user_verify_the_delete_address_message(String string) {
		int statusCode = getStatusCode(response);

		LoginStep.commonVariables.setStatusCode(statusCode);
		DeleteAddress_Output_pojo deleteAddress_Output_pojo = response.as(DeleteAddress_Output_pojo.class);
		String message = deleteAddress_Output_pojo.getMessage();
		Assert.assertEquals(message, string, "Verify Address Deleted?");
	}
}
