package com.example.QuanLyCongViec.dto.response;

import com.example.QuanLyCongViec.base.BaseDto;
import com.example.QuanLyCongViec.entity.CongViecEntity;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
public class CongViecDto extends BaseDto {

    private String name;

    private String description;

    private String deadline;

    public CongViecDto(CongViecEntity entity) {
        super(entity);

        this.name = entity.getName();
        this.description = entity.getDescription();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.deadline = dateFormat.format(entity.getDeadline());
    }
}
