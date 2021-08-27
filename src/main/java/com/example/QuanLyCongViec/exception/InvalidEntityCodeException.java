package com.example.QuanLyCongViec.exception;

import lombok.Getter;

@Getter
public class InvalidEntityCodeException extends RuntimeException {
    private final String message;

    public InvalidEntityCodeException(String code) {
        message = code + " is not a valid UUID code";
    }
}
