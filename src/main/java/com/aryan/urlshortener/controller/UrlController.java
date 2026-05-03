package com.aryan.urlshortener.controller;

import com.aryan.urlshortener.dto.CreateShortUrlRequest;
import com.aryan.urlshortener.dto.CreateShortUrlResponse;
import com.aryan.urlshortener.service.ShortCodeGenerator;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/urls")
public class UrlController {
    private final ShortCodeGenerator shortCodeGenerator;
    public UrlController(ShortCodeGenerator shortCodeGenerator) {
        this.shortCodeGenerator = shortCodeGenerator;
    }
    @PostMapping
    public CreateShortUrlResponse createShortUrl(
        @Valid @RequestBody CreateShortUrlRequest request
    ){
        String linkString=request.getCustomLink();
        if(linkString!=null && !linkString.isBlank())
        {
            String shortUrl="http://localhost:8080/"+linkString;
            System.out.println(shortUrl);
            return new CreateShortUrlResponse(shortUrl, true);
        }
        linkString=shortCodeGenerator.generateShortCode();
        String shortUrl="http://localhost:8080/"+linkString;
        System.out.println(shortUrl);
        return new CreateShortUrlResponse(shortUrl, true);
    }
}
