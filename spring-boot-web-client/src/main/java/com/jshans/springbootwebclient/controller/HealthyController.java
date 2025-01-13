package com.jshans.springbootwebclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthyController {

    @Value("${spring.application.name}")
    private String applicationName;

    @GetMapping("/health-check")
    public ResponseEntity<String> getHealthyCheck() {
        return new ResponseEntity<>(applicationName, HttpStatus.OK);
    }
}
