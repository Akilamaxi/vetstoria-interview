package com.interview.assignment.controller;

import com.interview.assignment.domain.dto.SuccessMessage;
import com.interview.assignment.service.RateLimitService;
import com.interview.assignment.util.ResponseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RequestMapping("/ip-trace")
@RestController
public class DemoController {

    private final RateLimitService rateLimitService;

    public DemoController(RateLimitService rateLimitService) {
        this.rateLimitService = rateLimitService;
    }

    @GetMapping
    public ResponseEntity<SuccessMessage> check(@RequestParam("seconds") int seconds){
        return ResponseUtils.wrapSuccess(
                Collections.singletonMap("ipList",rateLimitService.getAllIpAddr(seconds)),
                HttpStatus.OK);
    }
}
