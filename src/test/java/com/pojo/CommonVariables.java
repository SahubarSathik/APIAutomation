package com.pojo;

public class CommonVariables {
	public static int address_id;
	public static String logtoken;
	public static int statusCode;

	public static int getStatusCode() {
		return statusCode;
	}

	public static void setStatusCode(int statusCode) {
		CommonVariables.statusCode = statusCode;
	}

	public static int getAddress_id() {
		return address_id;
	}

	public static void setAddress_id(int address_id) {
		CommonVariables.address_id = address_id;
	}

	public static String getLogtoken() {
		return logtoken;
	}

	public static void setLogtoken(String logtoken) {
		CommonVariables.logtoken = logtoken;
	}

}
