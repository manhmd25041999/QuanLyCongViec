package com.example.QuanLyCongViec.dto.request;

import lombok.Getter;

import java.util.Date;

@Getter
public class CreateCongViecDto {

    private String code;

    private String name;

    private String description;

    private String deadline;
}
