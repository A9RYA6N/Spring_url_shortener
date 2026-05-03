package com.aryan.urlshortener.service;

import java.security.SecureRandom;

import org.springframework.stereotype.Service;

@Service
public class ShortCodeGenerator {
    public String generateShortCode()
    {
        String chars="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random=new SecureRandom();
        StringBuilder str=new StringBuilder();
        for(int i=0; i<6; i++)
        {
            int index=random.nextInt(chars.length());
            str.append(chars.charAt(index));
        }
        System.out.println("String: "+str.toString());
        return str.toString();
    }
}
