package com.interview.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * RateLimitException defined for throw exception if the remote ip address rate limit is exceeded per given TimeUnit.
 * This will produce standard 429 http status with Too many requests message
 */
@ResponseStatus(code = HttpStatus.TOO_MANY_REQUESTS, reason = "Too many requests")
public class RateLimitException extends RuntimeException{
}
