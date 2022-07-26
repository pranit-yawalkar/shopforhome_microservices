package com.wipro.discountservice.exceptions;

/*
 * ResourceNotFoundException will be executed 
 * if some resource does not exist.
 */
public class ResourceNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String message) {
		super(message);
	}

}
