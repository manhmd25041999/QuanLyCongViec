package com.example.QuanLyCongViec.base;

import com.example.QuanLyCongViec.exception.InvalidEntityCodeException;

import java.util.UUID;

public class UuidUtils {
    public static UUID getUuidFromStringOrElseThrowError(String code) {
        if (code == null) throw new InvalidEntityCodeException(null);

        try {
            return UUID.fromString(code);
        } catch (IllegalArgumentException e) {
            throw new InvalidEntityCodeException(code);
        }
    }

    public static UUID getUuidFromStringOrElseNull(String code) {
        if (code == null) return null;

        try {
            return UUID.fromString(code);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
