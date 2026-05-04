package com.aryan.urlshortener.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.aryan.urlshortener.dto.*;
import com.aryan.urlshortener.model.ShortUrl;
import com.aryan.urlshortener.repository.ShortUrlRepo;
import com.aryan.urlshortener.service.ShortCodeGenerator;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/urls")
public class UrlController {
    private final ShortCodeGenerator shortCodeGenerator;
    private final ShortUrlRepo shortUrlRepo;

    public UrlController(ShortCodeGenerator shortCodeGenerator, ShortUrlRepo shortUrlRepo) {
        this.shortCodeGenerator = shortCodeGenerator;
        this.shortUrlRepo = shortUrlRepo;
    }

    @PostMapping
    public ResponseEntity<?> createShortUrl(
        @Valid @RequestBody CreateShortUrlRequest request
    ){
        String originalUrl=request.getOriginalUrl();
        String linkString=request.getCustomLink();
        ShortUrl shortUrlEntity=new ShortUrl();

        if(linkString==null || linkString.isBlank())
        {
            linkString=shortCodeGenerator.generateShortCode();
            String shortUrl="http://localhost:8080/"+linkString;

            shortUrlEntity.setOriginalUrl(originalUrl);
            shortUrlEntity.setShortUrl(linkString);
            shortUrlRepo.save(shortUrlEntity);

            System.out.println(shortUrl);
            return ResponseEntity.status(HttpStatus.CREATED).body(new CreateShortUrlResponse(shortUrl, true));
        }

        boolean exists=shortUrlRepo.existsByShortUrl(linkString);

        if(exists)
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(false, "Link already in use! Please use another"));
        }
        String shortUrl="http://localhost:8080/"+linkString;

        shortUrlEntity.setOriginalUrl(originalUrl);
        shortUrlEntity.setShortUrl(linkString);
        shortUrlRepo.save(shortUrlEntity);

        System.out.println(shortUrl);
        return ResponseEntity.status(HttpStatus.CREATED).body(new CreateShortUrlResponse(shortUrl, true));
    }
}
