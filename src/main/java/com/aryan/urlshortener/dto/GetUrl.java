package com.aryan.urlshortener.dto;

public class GetUrl {
    private Integer clickCount;
    private Boolean success;
    private String originalUrl;
    private String shortUrl;

    public GetUrl(Boolean success, Integer clickCount, String originalUrl, String shortUrl)
    {
        this.clickCount=clickCount;
        this.success=success;
        this.originalUrl=originalUrl;
        this.shortUrl=shortUrl;
    }

    public Boolean getSuccess()
    {
        return success;
    }

    public Integer getClickCount()
    {
        return clickCount;
    }

    public String getOriginalUrl()
    {
        return originalUrl;
    }

    public String getShortUrl()
    {
        return shortUrl;
    }
}
