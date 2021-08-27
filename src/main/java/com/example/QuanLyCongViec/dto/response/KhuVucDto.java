package com.example.QuanLyCongViec.dto.response;

import com.example.QuanLyCongViec.base.BaseDto;
import com.example.QuanLyCongViec.base.BaseEntity;
import com.example.QuanLyCongViec.entity.KhuVucEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class KhuVucDto extends BaseDto {

    private String name;

    private String nameTrangTrai;

    private List<BangPhanCongDto> bangPhanCongDtos;


    public KhuVucDto(KhuVucEntity entity, List<BangPhanCongDto> bangPhanCongDtos) {
        super(entity);
        this.name = entity.getName();
        this.nameTrangTrai = entity.getTrangTraiEntity().getName();
        if (bangPhanCongDtos != null) {
            this.bangPhanCongDtos = bangPhanCongDtos;
        }
    }
}
