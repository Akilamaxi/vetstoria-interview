package com.interview.assignment.interceptor;

import com.interview.assignment.exception.RateLimitException;
import com.interview.assignment.service.RateLimitService;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * RateLimitInterceptor is basically similar to a Servlet Filter which enhance with throttling feature
 */
public class RateLimitInterceptor implements HandlerInterceptor {
    public static final String X_FORWARDED_FOR = "X-Forwarded-For";
    private final RateLimitService rateLimitService;

    public RateLimitInterceptor(RateLimitService rateLimitService) {
        this.rateLimitService = rateLimitService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /* throttle inputs are hardcoded due to limited time period of the assignment */
        final boolean isLimitExceed = rateLimitService.throttle(request.getHeader(X_FORWARDED_FOR), TimeUnit.MINUTES,100);
        if(!isLimitExceed){
            throw new RateLimitException();
        }
        return true;
    }
}
