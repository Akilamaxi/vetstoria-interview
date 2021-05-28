package com.interview.assignment.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class provides utility methods to format java util Date and LocalDateTime.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateTimeUtils {

    public static final String STANDARD_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * This will format the given java util Date to String
     * @param date current timestamp
     * @return String format of given java util Date
     */
    public static String format(Date date) {
        return new SimpleDateFormat(STANDARD_DATE_FORMAT).format(date);
    }

}