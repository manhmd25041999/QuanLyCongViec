package com.example.QuanLyCongViec.dto.request;

import lombok.Getter;

import java.util.List;

@Getter
public class CreateBangPhanCongDto {

    private String code;

    private String codeCongViec;

    private List<String> codeAccountEmployee;

    private String codeKhuVuc;
}
