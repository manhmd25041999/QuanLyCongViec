package com.example.QuanLyCongViec.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    EMPLOYEE("EMPLOYEE"),
    MANAGER("MANAGER"),
    OWNER("OWNER");

    private String name;

}
