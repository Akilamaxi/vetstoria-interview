package com.interview.assignment.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Wrapper object for success response
 */
@Getter
@Setter
@ToString
@Builder
public class SuccessMessage {
    private String message;
    private String timestamp;
    private Object data;
}
