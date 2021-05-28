package com.interview.assignment.ratelimiter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Class holding rate limit information
 */
public class Gauge {
    private final int rateLimit;
    private final long milliSeconds;
    private final List<Long> timeStamps;

    /**
     * Construct a gauge with given time units and rate limit
     * @param timeUnit TimeUnit to measure the number of calls
     * @param rateLimit max rate limit of calls per TimeUnit
     */
    public Gauge(TimeUnit timeUnit, int rateLimit) {
        this.rateLimit = rateLimit;
        this.milliSeconds = timeUnit.toMillis(1);
        /* Initiate synchronizedList to timeStamps due to the thread safety purpose in concurrent transactions */
        this.timeStamps = Collections.synchronizedList(new ArrayList<>());
    }

    /**
     * Returns if the rate limit is exceeded per given TimeUnit if not add current timestamp to gauge cache
     * @return boolean value of the rate limit is exceeded
     */
    public boolean limit(){
        if (timeStamps.size() >= rateLimit) {
            return false;
        }else {
            timeStamps.add(System.currentTimeMillis());
            return true;
        }
    }

    /**
     *  Remove the oldest timestamp recorded in timeStamps
     */
    public void removeOldestTimeStamp(){
        long threshold = System.currentTimeMillis() - milliSeconds;
        timeStamps.removeIf(tm -> tm < threshold);
    }

    /**
     * Returns the last recorded timeStamp in milliseconds
     * @return last recorded timeStamp
     */
    public Long retrieveLastTimeStamp(){
         return timeStamps.get(timeStamps.size()-1);
    }

}
