package com.aryan.urlshortener.dto;

public class GetUrlAnalytics {
    private Integer clickCount;
    private Boolean success;

    public GetUrlAnalytics(Boolean success, Integer clickCount)
    {
        this.success=success;
        this.clickCount=clickCount;
    }

    public Integer getClickCount()
    {
        return clickCount;
    }

    public Boolean getSucccess()
    {
        return success;
    }
}
