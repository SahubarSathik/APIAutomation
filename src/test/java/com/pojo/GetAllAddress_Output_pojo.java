package com.pojo;

import java.util.ArrayList;

public class GetAllAddress_Output_pojo {
	private int status;
	private String message;
	public ArrayList<Datum> data;
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
