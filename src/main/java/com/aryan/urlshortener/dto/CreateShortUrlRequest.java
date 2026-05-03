package com.aryan.urlshortener.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateShortUrlRequest {
    @NotBlank(message = "URL cannot be empty")
    private String originalUrl;

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    private String customLink;

    public String getCustomLink() {
        return customLink;
    }

    public void setCustomLink(String customLink) {
        this.customLink = customLink;
    }
}
