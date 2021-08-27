package com.example.QuanLyCongViec.base;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class BaseDto implements Serializable {
    protected UUID code;

    protected BaseDto(BaseEntity entity) {
        if (entity == null) return;
        code = entity.getCode();
    }
}
