package com.wipro.discountservice.response;

import java.util.Date;

/*
 * ApiResponse to send response to the client side
 * along with a status and message.
 */
public class ApiResponse {

	private boolean success;
	private String message;

	private Date date;

	public ApiResponse(boolean success, String message, Date date) {
		super();
		this.success = success;
		this.message = message;
		this.date = date;
	}

	public ApiResponse() {
		super();
	}

	public ApiResponse(boolean success, String message) {
		this.success = success;
		this.message = message;
		this.date = new Date();
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
