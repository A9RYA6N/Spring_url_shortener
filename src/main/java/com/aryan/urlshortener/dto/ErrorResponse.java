package com.aryan.urlshortener.dto;

public class ErrorResponse {
    private Boolean success;
    private String message;

    public ErrorResponse(Boolean success, String message)
    {
        this.success=success;
        this.message=message;
    }

    public Boolean getSuccess()
    {
        return success;
    }

    public String getMessage()
    {
        return message;
    }
}
