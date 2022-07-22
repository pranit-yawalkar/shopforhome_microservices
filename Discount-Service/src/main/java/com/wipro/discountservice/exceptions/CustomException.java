package com.wipro.discountservice.exceptions;

public class CustomException extends IllegalArgumentException {

	public CustomException(String message) throws Exception {
		super(message);
	}
}
