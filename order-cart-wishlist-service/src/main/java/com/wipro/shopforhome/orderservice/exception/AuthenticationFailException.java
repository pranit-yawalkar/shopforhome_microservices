package com.wipro.shopforhome.orderservice.exception;

/*
 * AuthenticationFailException will be executed 
 * if user has entered bad credentials.
 */
public class AuthenticationFailException extends IllegalArgumentException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AuthenticationFailException(String message) {
        super(message);
    }
}
