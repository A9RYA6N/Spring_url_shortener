package com.aryan.urlshortener.dto;

public class CreateShortUrlResponse {
    private String shortUrl;
    private Boolean success;

    public CreateShortUrlResponse(String shortUrl, Boolean success)
    {
        this.shortUrl=shortUrl;
        this.success=success;
    }

    public String getShortUrl()
    {
        return shortUrl;
    }

    public boolean getSuccess()
    {
        return success;
    }
}
