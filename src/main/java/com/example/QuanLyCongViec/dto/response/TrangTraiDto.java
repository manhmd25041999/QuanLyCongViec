package com.example.QuanLyCongViec.dto.response;

import com.example.QuanLyCongViec.base.BaseDto;
import com.example.QuanLyCongViec.base.BaseEntity;
import com.example.QuanLyCongViec.entity.TrangTraiEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class TrangTraiDto extends BaseDto {

    private String name;

    private List<KhuVucDto> khuVucDtos;

    private List<AccountDto> accountDtos;

    public TrangTraiDto(TrangTraiEntity entity, List<KhuVucDto> khuVucDtos) {
        super(entity);
        this.name = entity.getName();
        if (khuVucDtos != null) {
            this.accountDtos = entity.getAccountEntities().stream().map(AccountDto::new).collect(Collectors.toList());
            this.khuVucDtos = khuVucDtos;
        }

    }
}
