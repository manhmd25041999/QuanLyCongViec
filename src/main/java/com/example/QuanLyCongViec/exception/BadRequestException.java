package com.example.QuanLyCongViec.exception;

import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException {
    private final String message;

    public BadRequestException(String s) {
        message = s;
    }
}
