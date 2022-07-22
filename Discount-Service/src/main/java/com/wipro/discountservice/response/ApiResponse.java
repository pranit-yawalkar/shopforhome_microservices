package com.wipro.discountservice.response;

import java.time.LocalDateTime;
import java.util.Date;

public class ApiResponse {

    private final boolean success;
    private final  String message;

    private Date date;

    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
        this.date=new Date();
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
