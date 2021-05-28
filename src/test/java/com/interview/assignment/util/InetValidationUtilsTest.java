package com.interview.assignment.util;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class InetValidationUtilsTest {


    public static final String INET_4_ADDRESS = "192.168.1.1";
    public static final String INVALID_INET_4_ADDRESS = "1223.168.1.1";
    public static List<String> VALID_INET_4_ADDRESS_LIST;


    @Before
    public void setUp() throws Exception {
         VALID_INET_4_ADDRESS_LIST = Stream.of(
                 "0.0.0.0",
                 "0.0.0.1",
                 "127.0.0.1",
                 "1.2.3.4",              // 0-9
                 "11.1.1.0",             // 10-99
                 "101.1.1.0",            // 100-199
                 "201.1.1.0",            // 200-249
                 "255.255.255.255",      // 250-255
                 "192.168.1.1",
                 "192.168.1.255",
                 "100.100.100.100",
                 "100.108.100.100",
                 "100.100.22.100"
         ).collect(Collectors.toList());
    }

    @Test
    public void isValidInet4Address() {
        final boolean isValid = InetValidationUtils.isValidInet4Address(INET_4_ADDRESS);
        assertTrue(isValid);
    }

    @Test
    public void isNotValidInet4Address() {
        final boolean isValid = InetValidationUtils.isValidInet4Address(INVALID_INET_4_ADDRESS);
        assertFalse(isValid);
    }

    @Test
    public void anyMatch() {
        final List<String> matchedList = InetValidationUtils.anyMatch(VALID_INET_4_ADDRESS_LIST, INET_4_ADDRESS);
        assertThat(matchedList.size(), Matchers.greaterThan(0));
    }
}