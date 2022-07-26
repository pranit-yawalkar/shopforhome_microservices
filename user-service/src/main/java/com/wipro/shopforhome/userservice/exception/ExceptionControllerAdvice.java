package com.wipro.shopforhome.userservice.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/*
 * Exception Controller Advice to send the exception message as a 
 * response entity to the client side.
 */
@ControllerAdvice
public class ExceptionControllerAdvice extends IllegalArgumentException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ExceptionHandler(value = CustomException.class)
    public final ResponseEntity<String> handleCustomException(CustomException customException) {
        return new ResponseEntity<>(customException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = AuthenticationFailException.class)
    public final ResponseEntity<String> handleAuthenticationFailException(AuthenticationFailException authenticationFailException) {
        return new ResponseEntity<>(authenticationFailException.getMessage(), HttpStatus.BAD_REQUEST);
    }
}

