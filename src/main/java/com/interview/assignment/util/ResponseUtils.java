package com.interview.assignment.util;

import com.interview.assignment.domain.dto.SuccessMessage;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;

/**
 *  Wrapper class for produce endpoint outcomes
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseUtils {
    private static final String SUCCESS_MESSAGE = "success";

    /**
     * Returns wrapped SuccessMessage in ResponseEntity
     * @param object outcome object which needs to wrap
     * @param httpStatus HttpStatus for success response eg:(200,201)
     * @return
     */
    public static ResponseEntity<SuccessMessage> wrapSuccess(Object object, HttpStatus httpStatus) {
        SuccessMessage successMessage = SuccessMessage
                .builder()
                .data(object)
                .message(SUCCESS_MESSAGE)
                .timestamp(DateTimeUtils.format(new Date()))
                .build();
        return ResponseEntity.status(httpStatus).body(successMessage);
    }

}