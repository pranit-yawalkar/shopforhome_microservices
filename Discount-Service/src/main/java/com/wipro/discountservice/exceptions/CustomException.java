package com.wipro.discountservice.exceptions;

/*
 * CustomException will be executed if some other error occurs. 
 */
public class CustomException extends IllegalArgumentException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomException(String message) throws Exception {
		super(message);
	}
}
