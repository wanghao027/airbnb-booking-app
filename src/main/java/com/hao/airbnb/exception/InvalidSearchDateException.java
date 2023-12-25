package com.hao.airbnb.exception;

public class InvalidSearchDateException extends RuntimeException {
    public InvalidSearchDateException(String message) {
        super(message);
    }
}
