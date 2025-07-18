package com.kamalesh.server.controllers;

import com.kamalesh.server.dtos.ClickEventDto;
import com.kamalesh.server.dtos.UrlMappingDTO;
import com.kamalesh.server.models.ClickEvent;
import com.kamalesh.server.models.User;
import com.kamalesh.server.services.UrlMappingService;
import com.kamalesh.server.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/urls")
@AllArgsConstructor
public class UrlMappingController {

    private UrlMappingService urlMappingService;
    private UserService userService;
    @PostMapping("/shorten")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UrlMappingDTO> shortenUrl(@RequestBody Map<String,String> request , Principal principal){
        String originalUrl=request.get("originalUrl");
        User user = userService.findByUsername(principal.getName());
        UrlMappingDTO urlMappingDTO= urlMappingService.shortenUrl(originalUrl,user);
        return ResponseEntity.ok(urlMappingDTO);
    }
    @GetMapping("/myUrls")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<UrlMappingDTO>> getUserUrls(Principal principal){
        User user=userService.findByUsername(principal.getName());
        List<UrlMappingDTO> Urls=urlMappingService.getUrlsByUser(user);
        return ResponseEntity.ok(Urls);
    }

    @GetMapping("/analytics/{shortUrl}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<ClickEventDto>> getClickEvents(@PathVariable String shortUrl, @RequestParam("startDate") String startDate,
                                                              @RequestParam("endDate") String endDate, Principal principal){
        DateTimeFormatter formatter=DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime start=LocalDateTime.parse(startDate,formatter);
        LocalDateTime end=LocalDateTime.parse(startDate,formatter);
        List<ClickEventDto> Events=urlMappingService.getClickEventsByDate(shortUrl,start,end);
        return ResponseEntity.ok(Events);
    }
    @GetMapping("/totalClicks")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Map<LocalDate, Long>> getTotalClicksByDate(Principal principal,
                                                                     @RequestParam("startDate") String startDate,
                                                                     @RequestParam("endDate") String endDate){
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        User user = userService.findByUsername(principal.getName());
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);
        Map<LocalDate, Long> totalClicks = urlMappingService.getTotalClicksByUserAndDate(user, start, end);
        return ResponseEntity.ok(totalClicks);
    }

}
