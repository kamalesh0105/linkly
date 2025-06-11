package com.kamalesh.server.services;

import com.kamalesh.server.dtos.UrlMappingDTO;
import com.kamalesh.server.models.UrlMapping;
import com.kamalesh.server.models.User;
import com.kamalesh.server.repository.UrlMappingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class UrlMappingService {
    private UserService userService;
    private UrlMappingRepository urlMappingRepository;

    public UrlMappingDTO shortenUrl(String originalUrl, User user){
    String shortUrl=URlShortener(originalUrl);
    UrlMapping urlMapping=new UrlMapping();
    urlMapping.setOriginalUrl(originalUrl);
    urlMapping.setShortUrl(shortUrl);
    urlMapping.setUser(user);
    urlMapping.setCreatedDate(LocalDateTime.now());
    UrlMapping savedUrlMapping=urlMappingRepository.save(urlMapping);
    return convertToDto(savedUrlMapping);
    }
    private UrlMappingDTO convertToDto(UrlMapping urlMapping){
        UrlMappingDTO urlMappingDTO = new UrlMappingDTO();
        urlMappingDTO.setId(urlMapping.getId());
        urlMappingDTO.setOriginalUrl(urlMapping.getOriginalUrl());
        urlMappingDTO.setShortUrl(urlMapping.getShortUrl());
        urlMappingDTO.setClickCount(urlMapping.getClickCount());
        urlMappingDTO.setCreatedDate(urlMapping.getCreatedDate());
        urlMappingDTO.setUsername(urlMapping.getUser().getUsername());
        return urlMappingDTO;
    }
    public String URlShortener(String originalUrl){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        Random random = new Random();
        StringBuilder shortUrl = new StringBuilder(8);

        for (int i = 0; i < 8; i++) {
            shortUrl.append(characters.charAt(random.nextInt(characters.length())));
        }
        return shortUrl.toString();
    }
}
