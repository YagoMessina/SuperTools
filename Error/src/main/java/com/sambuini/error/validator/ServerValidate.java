package com.sambuini.error.validator;

import com.sambuini.error.entity.ApiException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import java.util.Collection;

public class ServerValidate {

    public static final HttpStatus STATUS = HttpStatus.INTERNAL_SERVER_ERROR;

    public static void isTrue(boolean condition, String message) {
        if (!condition) {
            throw new ApiException(message, STATUS);
        }
    }

    public static <T> void isNull(T object, String message) {
        if (object != null) {
            throw new ApiException(message, STATUS);
        }
    }

    public static <T> void notNull(T object, String message) {
        if (object == null) {
            throw new ApiException(message, STATUS);
        }
    }

    public static <T extends Collection> void isEmpty(T entity, String message) {
        if (entity == null || !entity.isEmpty()) {
            throw new ApiException(message, STATUS);
        }
    }

    public static <T extends CharSequence> void isEmpty(T entity, String message) {
        if (entity == null || entity.length() != 0) {
            throw new ApiException(message, STATUS);
        }
    }

    public static <T extends Collection> void notEmpty(T entity, String message) {
        if(entity == null || entity.isEmpty()) {
            throw new ApiException(message, STATUS);
        }
    }

    public static <T extends CharSequence> void notEmpty(T entity, String message) {
        if(entity == null || entity.length() == 0) {
            throw new ApiException(message, STATUS);
        }
    }

    public static <T extends CharSequence> void isBlank(T entity, String message) {
        if(entity == null || !StringUtils.isBlank(entity)) {
            throw new ApiException(message, STATUS);
        }
    }

    public static <T extends CharSequence> void notBlank(T entity, String message) {
        if(entity == null || StringUtils.isBlank(entity)) {
            throw new ApiException(message, STATUS);
        }
    }
}
