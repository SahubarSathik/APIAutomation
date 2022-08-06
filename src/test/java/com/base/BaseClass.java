package com.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class BaseClass {
	public RequestSpecification reqSpec;
	public Response response;

	public String getPropertyValue(String key) throws FileNotFoundException, IOException {
		Properties properties = new Properties();
		properties.load(
				new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties"));
		Object object = properties.get(key);
		String value = (String) object;
		return value;
	}

	public void addHeaders(Headers header) {
		reqSpec = RestAssured.given().headers(header);		
	}
	public void addBody (Object body) {
		
		reqSpec= reqSpec.body(body);
	}
	public void formData(String fileLocation) {
		reqSpec = reqSpec.multiPart("profile_picture",new File(fileLocation));
		
	}

	public void addHeader(String key, String value) {
		reqSpec = RestAssured.given().header(key, value);
	}

	public void queryParam(String key, String value) {
		reqSpec = reqSpec.queryParam(key, value);
	}

	public void pethParam(String key, String value) {
		reqSpec = reqSpec.pathParam(key, value);
	}

	public void basicAuth(String userName, String password) {
		reqSpec = reqSpec.auth().preemptive().basic(userName, password);
	}

	public void addBody(String body) {
		reqSpec = reqSpec.body(body);
	}

	public Response requestType(String reqType, String endPoint) {

		switch (reqType) {
		case "GET":
			response = reqSpec.log().all().get(endPoint);
			break;
		case "POST":
			response = reqSpec.log().all().post(endPoint);
			break;
		case "PUT":
			response = reqSpec.log().all().put(endPoint);
			break;
		case "DELETE":
			response = reqSpec.log().all().delete(endPoint);
			break;

		default:
			break;
		}

		return response;
	}

	public int getStatusCode(Response response) {
		int statusCode = response.getStatusCode();
		return statusCode;
	}

	public ResponseBody getBody(Response response) {
		ResponseBody body = response.getBody();
		return body;
	}

	public String getBodyAsString(Response response) {
		String asString = getBody(response).asString();
		return asString;
	}

	public String getBodyAsPrettyString(Response response) {
		String jsonFormat = getBody(response).asPrettyString();
		return jsonFormat;

	}

}
