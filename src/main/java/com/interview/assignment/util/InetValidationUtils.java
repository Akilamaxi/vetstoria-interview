package com.interview.assignment.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.validator.routines.InetAddressValidator;

import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * This class provides utility methods to validate a candidate IP address.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InetValidationUtils {

    /**
     * Validates an IPv4 address. Returns true if valid.
     * @param inet4Address the IPv4 address to validate
     * @return true if the argument contains a valid IPv4 address
     */
    public static boolean isValidInet4Address(final String inet4Address) {
        Objects.requireNonNull(inet4Address);
        return InetAddressValidator.getInstance().isValidInet4Address(inet4Address);
    }

    /**
     * Returns true if the argument contains any octet.
     * @param source ArrayList of IP addresses.
     * @param query The value to octet.
     * @return true if the argument contains any octet
     */
    public static List<String> anyMatch(List<String> source, String query){
        return source.stream().filter(s -> InetValidationUtils.isValidInet4Address(s) && isContain(s,query))
                .collect(Collectors.toList());
    }

    /**
     * Returns true if the argument contains any octet.
     */
    private static boolean isContain(String source, String subItem){
        String pattern = "\\b"+subItem+"\\b";
        Pattern p=Pattern.compile(pattern);
        Matcher m=p.matcher(source);
        return m.find();
    }
}
