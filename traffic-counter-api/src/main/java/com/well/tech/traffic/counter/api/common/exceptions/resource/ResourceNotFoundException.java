package com.well.tech.traffic.counter.api.common.exceptions.resource;

import com.well.tech.traffic.counter.api.common.exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends BaseException {
    public ResourceNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND.value());
    }
}