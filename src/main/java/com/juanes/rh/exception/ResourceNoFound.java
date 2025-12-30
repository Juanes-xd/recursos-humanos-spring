package com.juanes.rh.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Resource not found")
public class ResourceNoFound extends RuntimeException {
    public ResourceNoFound(String message) {
        super(message);
    }
}
