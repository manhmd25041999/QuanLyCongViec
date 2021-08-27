package com.example.QuanLyCongViec.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionHandlder {
    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<Map<String, String>> handleException(BadRequestException ex) {
        Map<String, String> map = new HashMap<>();
        map.put("loi", ex.getMessage());
        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({LoginFailExceptionHehe.class})
    public ResponseEntity<Map<String, String>> handleLoginFailExceptionHehe(LoginFailExceptionHehe ex) {
        Map<String, String> map = new HashMap<>();
        map.put("loi", ex.getMessage());
        return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
    }

}
