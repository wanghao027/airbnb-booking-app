package com.hao.airbnb.exception;

public class GeoCodingException extends RuntimeException {
    public GeoCodingException(String message) {
        super(message);
    }
}
