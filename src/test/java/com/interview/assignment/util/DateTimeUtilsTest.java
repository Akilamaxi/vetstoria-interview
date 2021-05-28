package com.interview.assignment.util;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class DateTimeUtilsTest {

    public static final Date CURRENT_TIMESTAMP = new Date(233345223232L);

    @Test
    public void format() {
        final String formattedDate = DateTimeUtils.format(CURRENT_TIMESTAMP);
        assertEquals(formattedDate,"1977-05-24 23:37:03");
    }
}