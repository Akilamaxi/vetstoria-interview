package com.interview.assignment.service.impl;

import com.interview.assignment.AbstractTest;
import com.interview.assignment.service.RateLimitService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class RateLimitServiceImplTest extends AbstractTest {

    public static final String REMOTE_ADDR = "192.168.1.1";

    @Autowired
    RateLimitService rateLimitService;

    @Test
    public void throttleIsLimitNotExceeded() {
        boolean isLimitExceeded;
        for (int i = 0; i < 2; i++) {
            isLimitExceeded = rateLimitService.throttle(REMOTE_ADDR, TimeUnit.MINUTES, 2);
            Assert.isTrue(isLimitExceeded,"Should be okay");
        }

    }
    
    @Test
    public void throttleIsLimitExceeded() {
        boolean isLimitExceeded = false;
        for (int i = 0; i < 2; i++) {
            isLimitExceeded = rateLimitService.throttle(REMOTE_ADDR, TimeUnit.MINUTES, 1);

        }
        Assert.isTrue(!isLimitExceeded,"Should not be okay");
    }

    @Test
    public void getAllIpAddr() {
        boolean isLimitExceeded = rateLimitService.throttle(REMOTE_ADDR, TimeUnit.MINUTES, 2);
        final List<String> allIpAddr = rateLimitService.getAllIpAddr(30);
        org.junit.Assert.assertThat(allIpAddr.size(), Matchers.greaterThan(0));
    }
}