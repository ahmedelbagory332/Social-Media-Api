package com.social.posts.core.errorModel;


import java.time.LocalDateTime;

public class ErrorDetails {

    private LocalDateTime timestamp;
    private String message;
    private int statusCode;



    public ErrorDetails(LocalDateTime timestamp, String message, int statusCode) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.statusCode = statusCode;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }


}
