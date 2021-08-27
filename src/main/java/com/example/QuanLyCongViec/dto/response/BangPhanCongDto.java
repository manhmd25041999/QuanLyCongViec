package com.example.QuanLyCongViec.dto.response;

import com.example.QuanLyCongViec.base.BaseDto;
import com.example.QuanLyCongViec.entity.AccountEntity;
import com.example.QuanLyCongViec.entity.BangPhanCongEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class BangPhanCongDto extends BaseDto {

    private String nameKhuVuc;

    private String nameTrangTrai;

    private CongViecDto congViecDto;

    private List<AccountDto> employees;

    private String trangThai;


    public BangPhanCongDto(BangPhanCongEntity entity, List<AccountEntity> accountEntities) {
        super(entity);
        this.nameKhuVuc = entity.getKhuVucEntity().getName();
        this.nameTrangTrai = entity.getKhuVucEntity().getTrangTraiEntity().getName();
        this.congViecDto = new CongViecDto(entity.getCongViecEntity());
        this.employees = accountEntities.stream().map(AccountDto::new).collect(Collectors.toList());
        this.trangThai = entity.getDanhGia();
    }
}
