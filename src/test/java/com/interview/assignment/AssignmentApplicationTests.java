package com.interview.assignment;

import com.interview.assignment.controller.DemoController;
import com.interview.assignment.service.RateLimitService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class AssignmentApplicationTests extends AbstractTest{

    @Autowired
    RateLimitService rateLimitService;

    @Autowired
    DemoController demoController;

    @Test
    void contextLoads() {
        assertThat(rateLimitService).isNotNull();
        assertThat(demoController).isNotNull();
    }



}
