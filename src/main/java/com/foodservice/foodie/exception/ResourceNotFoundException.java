package com.foodservice.foodie.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resource, String field, Object value) {
        super(String.format("%s not found for %s : %s", resource, field, value));
    }
}
