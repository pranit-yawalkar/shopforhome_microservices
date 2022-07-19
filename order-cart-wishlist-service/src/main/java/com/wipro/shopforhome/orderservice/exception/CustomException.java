package com.wipro.shopforhome.orderservice.exception;

public class CustomException extends IllegalArgumentException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomException(String message) {
        super(message);
    }
}
