package com.hao.airbnb.exception;

public class GCSUploadException extends RuntimeException {
    public GCSUploadException(String message) {
        super(message);
    }
}
