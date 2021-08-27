package com.example.QuanLyCongViec.dto.response;


import com.example.QuanLyCongViec.base.BaseDto;
import com.example.QuanLyCongViec.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UserDto extends BaseDto {

    private String name;

    private String address;

    private int age;

    public UserDto(UserEntity entity) {
        super(entity);
        name = entity.getName();
        address = entity.getAddress();
        age = entity.getAge();
    }
}
