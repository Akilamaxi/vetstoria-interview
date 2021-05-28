package com.interview.assignment.config;

import com.interview.assignment.interceptor.RateLimitInterceptor;
import com.interview.assignment.service.RateLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebConfig is a custom configuration class which implemented for register RateLimitInterceptor
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    RateLimitService rateLimitService;

    /**
     * Register RateLimitInterceptor into WebMvcConfigurer
     * @param registry InterceptorRegistry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RateLimitInterceptor(rateLimitService));
    }
}
