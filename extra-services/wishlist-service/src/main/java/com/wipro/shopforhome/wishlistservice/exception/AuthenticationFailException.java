package com.wipro.shopforhome.wishlistservice.exception;

public class AuthenticationFailException extends IllegalArgumentException {
    public AuthenticationFailException(String message) {
        super(message);
    }
}

