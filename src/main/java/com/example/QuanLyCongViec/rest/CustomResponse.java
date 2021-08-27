package com.example.QuanLyCongViec.rest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomResponse<T> {
    private int status;
    private T data;
    private String message;

    public static <T> CustomResponse<T> ok(T data) {
        CustomResponse<T> customResponse = new CustomResponse<>();
        customResponse.status = HttpStatus.OK.value();
        customResponse.data = data;

        return customResponse;
    }

    public static CustomResponse<Void> ok() {
        CustomResponse<Void> customResponse = new CustomResponse<>();
        customResponse.status = HttpStatus.OK.value();

        return customResponse;
    }

    public static <T> CustomResponse<T> error(HttpStatus errorStatus, String errorMessage) {
        CustomResponse<T> customResponse = new CustomResponse<>();
        customResponse.status = errorStatus.value();
        customResponse.message = errorMessage;

        return customResponse;
    }

    public static <T> CustomResponse<T> error(CustomResponse<?> response) {
        CustomResponse<T> customResponse = new CustomResponse<>();
        customResponse.status = response.getStatus();
        customResponse.message = response.getMessage();

        return customResponse;
    }

    public static <T> CustomResponse<T> error(HttpStatus errorStatus, String errorMessage, T data) {
        CustomResponse<T> customResponse = new CustomResponse<>();
        customResponse.status = errorStatus.value();
        customResponse.message = errorMessage;
        customResponse.data = data;

        return customResponse;
    }

    public static <T> CustomResponse<T> warning(T data, String warningMessage) {
        CustomResponse<T> customResponse = new CustomResponse<>();
        customResponse.status = HttpStatus.OK.value();
        customResponse.message = warningMessage;
        customResponse.data = data;

        return customResponse;
    }

    @SuppressWarnings("unused")
    public boolean isSuccess() {
        return status == HttpStatus.OK.value();
    }

    @JsonIgnore
    public boolean isFail() {
        return status != HttpStatus.OK.value();
    }
}
