package com.sambuini.error.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import com.sambuini.error.entity.ApiException;

@Getter
public class ErrorDTO {

    private final String message;

    private final Integer statusCode;

    public ErrorDTO(String message, HttpStatus statusCode) {
        this.message = message;
        this.statusCode = statusCode.value();
    }

    public ErrorDTO(ApiException apiException) {
        this(apiException.getMessage(), apiException.getHttpStatus());
    }
}
