package com.example.QuanLyCongViec.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DanhGia {
    DAHOANTHANH("Da hoan thanh"),
    CHUAHOANTHANH("Chua hoan thanh"),
    QUAHAN("Qua han");

    private String name;
}
