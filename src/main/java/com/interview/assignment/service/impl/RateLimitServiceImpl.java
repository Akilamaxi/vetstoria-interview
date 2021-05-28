package com.interview.assignment.service.impl;

import com.interview.assignment.ratelimiter.Gauge;
import com.interview.assignment.service.RateLimitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class RateLimitServiceImpl implements RateLimitService {
    /* Initiate ConcurrentHashMap to the cache due to the thread safety purpose in concurrent transactions */
    private static ConcurrentHashMap<String, Gauge> cache = new ConcurrentHashMap<>();

    public boolean throttle(String remoteAddr, TimeUnit timeUnit, int rateLimit){
        try{
            Gauge gauge = cache.computeIfAbsent(remoteAddr, s -> new Gauge(timeUnit, rateLimit));
            gauge.removeOldestTimeStamp();
            return gauge.limit();
        }catch (Exception ex){
            if(log.isDebugEnabled()){
                log.error("exception occurred while request throttling", ex);
            }
        }
        return true;
    }

    @Override
    public List<String> getAllIpAddr(int seconds) {
        List<String> ipList = new ArrayList<>();
        cache.forEach( (k,v) -> {
            final Long lastTimeStamp = v.retrieveLastTimeStamp();
            final Duration between = Duration.between(
                    Instant.ofEpochMilli(lastTimeStamp).atZone(ZoneId.systemDefault()).toLocalTime(),
                    LocalTime.now()
            );
            /* check last stored time difference is less than in given seconds period */
            if(between.getSeconds() < seconds){
                ipList.add(k);
            }
        });
        return ipList;
    }
}
