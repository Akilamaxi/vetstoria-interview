package com.interview.assignment.ratelimiter;

import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertNotNull;

public class GaugeTest {

    @Before
    public void setUp() throws Exception {
    }

    @SneakyThrows
    @Test
    public void limit() {
        Gauge gauge = new Gauge(TimeUnit.SECONDS,1);
        gauge.removeOldestTimeStamp();
        Assert.isTrue(gauge.limit(),"Initial Call");
        gauge.removeOldestTimeStamp();
        Assert.isTrue(!gauge.limit(),"Shouldn't be ok");
        Thread.sleep(1200);
        gauge.removeOldestTimeStamp();
        Assert.isTrue(gauge.limit(),"Shouldn be ok after 1200 milliseconds");

    }

    @Test
    public void retrieveLastTimeStamp() {
        Gauge gauge = new Gauge(TimeUnit.SECONDS,1);
        gauge.limit();
        final Long lastTimeStamp = gauge.retrieveLastTimeStamp();
        assertNotNull(lastTimeStamp);
    }
}