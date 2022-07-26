package com.wipro.shopforhome.orderservice.dto;


import java.time.LocalDateTime;

/*
 *	ResponseDTO to transfer data in the required 
 *	format to the client side 
 *
 */
public class ResponseDTO {
    private final boolean success;
    private final String message;

    public ResponseDTO(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getTimeStamp() {
        return LocalDateTime.now().toString();
    }
}

