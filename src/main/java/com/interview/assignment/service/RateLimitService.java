package com.interview.assignment.service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * RateLimitService is defined primarily by the rate at which permits are issued.
 * Defined in terms of permits rate limit per given TimeUnit. Also it serves list of ip addresses that has visited for given period in seconds
 */
public interface RateLimitService {

    /**
     * Returns if the rate limit is exceeded per given TimeUnit and store current timestamp against user ip address
     * @param remoteAddr address of the client or last proxy
     * @param timeUnit TimeUnit to measure the number of calls
     * @param rateLimit rate limit of calls per TimeUnit
     * @return
     */
    boolean throttle(String remoteAddr, TimeUnit timeUnit, int rateLimit);

    /**
     * Returns IP addresses that have visited in the last X seconds;
     * @param seconds time period in seconds
     * @return ArrayList of Ip addresses
     */
    List<String> getAllIpAddr(int seconds);
}
