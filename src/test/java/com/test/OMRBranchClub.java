package com.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.groovy.parser.antlr4.GroovyParser.AdditiveExprAltContext;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.endpoints.Endpoints;
import com.pojo.AddAddress_Input_Pojo;
import com.pojo.AddAddress_Output_Pojo;
import com.pojo.Datum;
import com.pojo.DeleteAddress_Input_pojo;
import com.pojo.DeleteAddress_Output_pojo;
import com.pojo.GetAllAddress_Output_pojo;
import com.pojo.Login_Output_Pojo;
import com.pojo.UpdateAddress_Input_pojo;
import com.pojo.UpdateAddress_Output_pojo;
import com.pojo.UpdateProfilePic_Output_pojo;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class OMRBranchClub extends BaseClass {

	String logtoken;
	int address_id;

	@Test(priority = 1)
	private void login() throws FileNotFoundException, IOException {
		// 1.Header
		addHeader("Content-Type", "application/json");

		// 2.Basic Auth
		basicAuth(getPropertyValue("username"), getPropertyValue("password"));

		// 3.Request Type
		Response response = requestType("POST", Endpoints.LOGIN);
		Login_Output_Pojo login_Output_Pojo = response.as(Login_Output_Pojo.class);

		int statusCode = getStatusCode(response);
		System.out.println(statusCode);

		Assert.assertEquals(statusCode, 200, "Verify Status Code");

		String message = login_Output_Pojo.getMessage();
		System.out.println(message);

		String first_name = login_Output_Pojo.getData().getFirst_name();
		System.out.println(first_name);

		Assert.assertEquals(first_name, "Sahubar", "Verify FirstName");

		logtoken = login_Output_Pojo.getData().getLogtoken();
	}

	@Test(priority = 2)
	private void createAddress() {

		// 1.header
		List<Header> h = new ArrayList<Header>();
		Header h1 = new Header("Content-Type", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		h.add(h1);
		h.add(h2);
		Headers headers = new Headers(h);
		addHeaders(headers);

		// 2.Add Data's
		AddAddress_Input_Pojo addAddress_Input_Pojo = new AddAddress_Input_Pojo("santhose", "Kumar", "9008007006",
				"Z apartment", 33, 3378, 101, "202020", "8/95 sakthi nagar", "home");
		addBody(addAddress_Input_Pojo);

		// 3.Method Type
		Response response = requestType("POST", Endpoints.ADDADDRESS);
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);
		AddAddress_Output_Pojo addAddress_Output_Pojo = response.as(AddAddress_Output_Pojo.class);
		String message = addAddress_Output_Pojo.getMessage();
		Assert.assertEquals(statusCode, 200, "VerifyStatusCode");
		Assert.assertEquals(message, "Address added successfully", "Verify Address Created");
		address_id = addAddress_Output_Pojo.getAddress_id();

	}

	@Test(priority = 3)
	private void updateAddress() {
		// 1.header
		List<Header> h = new ArrayList<Header>();
		Header h1 = new Header("Content-Type", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		h.add(h1);
		h.add(h2);
		Headers headers = new Headers(h);
		addHeaders(headers);

		UpdateAddress_Input_pojo updateAddress_Input_pojo = new UpdateAddress_Input_pojo(String.valueOf(address_id),
				"sakthi", "V", "894565238", "mount", 33, 21, 103, "568962", "57-84, Timondi colony", "office");

		addBody(updateAddress_Input_pojo);

		Response response = requestType("PUT", Endpoints.UPDATEADDRESS);
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, 200, "Verify Status Code");

		UpdateAddress_Output_pojo updateAddress_Output_pojo = response.as(UpdateAddress_Output_pojo.class);
		String message = updateAddress_Output_pojo.getMessage();
		Assert.assertEquals(message, "Address updated successfully", "Verify Address Updated");
	}

	@Test(priority = 4)
	private void getAllAddresses() {
		// 1.header
		List<Header> h = new ArrayList<Header>();
		Header h1 = new Header("Content-Type", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		h.add(h1);
		h.add(h2);
		Headers headers = new Headers(h);
		addHeaders(headers);

		Response response = requestType("GET", Endpoints.GETALLADDRESSES);
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, 200, "Verify Status Code");

		GetAllAddress_Output_pojo getAllAddress_Output_pojo = response.as(GetAllAddress_Output_pojo.class);
		String message = getAllAddress_Output_pojo.getMessage();
		Assert.assertEquals(message, "OK", "Verify OK");

	}

	@Test(priority = 5)
	private void deleteAddress() {
		// 1.header
		List<Header> h = new ArrayList<Header>();
		Header h1 = new Header("Content-Type", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		h.add(h1);
		h.add(h2);
		Headers headers = new Headers(h);
		addHeaders(headers);
		DeleteAddress_Input_pojo deleteAddress_Input_pojo = new DeleteAddress_Input_pojo(String.valueOf(address_id));
		addBody(deleteAddress_Input_pojo);
		Response response = requestType("DELETE", Endpoints.DELETEADDRESS);
		int statusCode = getStatusCode(response);
		Assert.assertEquals(statusCode, 200, "Verify Status Code");
		DeleteAddress_Output_pojo deleteAddress_Output_pojo = response.as(DeleteAddress_Output_pojo.class);
		String message = deleteAddress_Output_pojo.getMessage();
		Assert.assertEquals(message, "Address deleted successfully", "Verify Address Deleted?");

	}

	@Test(priority = 6)
	private void profilePicUpdate() {
		List<Header> h = new ArrayList<Header>();
		Header h1 = new Header("Content-Type", "multipart/form-data");

		Header h2 = new Header("Authorization", "Bearer " + logtoken);

		h.add(h1);
		h.add(h2);

		Headers headers = new Headers(h);
		addHeaders(headers);
		formData("C:\\Users\\Sathik\\Downloads\\img1.jpg");
		response = requestType("POST", Endpoints.UPDATEPROFILEPIC);
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		UpdateProfilePic_Output_pojo updateProfilePic_Output_pojo = response.as(UpdateProfilePic_Output_pojo.class);
		String message = updateProfilePic_Output_pojo.getMessage();
		Assert.assertEquals(message, "Profile updated Successfully", "Verify PrifilePicture Updates?");

	}

}
