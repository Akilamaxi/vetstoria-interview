package com.interview.assignment.util;

import com.interview.assignment.domain.dto.SuccessMessage;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ResponseUtilsTest {

    public static final String SAMPLE_OUTCOME_OBJECT = "Sample outcome object";
    private static final String SUCCESS_MESSAGE = "success";

    @Test
    public void wrapSuccess() {
        final ResponseEntity<SuccessMessage> wrapSuccess = ResponseUtils.wrapSuccess(SAMPLE_OUTCOME_OBJECT, HttpStatus.OK);
        assertEquals(wrapSuccess.getBody().getData(),SAMPLE_OUTCOME_OBJECT);
        assertEquals(wrapSuccess.getBody().getMessage(),SUCCESS_MESSAGE);
        assertTrue(wrapSuccess.getStatusCode().is2xxSuccessful());

    }
}