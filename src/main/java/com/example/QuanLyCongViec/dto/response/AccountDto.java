package com.example.QuanLyCongViec.dto.response;


import com.example.QuanLyCongViec.base.BaseDto;
import com.example.QuanLyCongViec.entity.AccountEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDto extends BaseDto {

    private String username;

    private String role;

    private UserDto userDto;

    private String nameTrangTrai;

    public AccountDto(AccountEntity entity) {
        super(entity);
        username = entity.getUsername();
        role = entity.getRole();
        userDto = new UserDto(entity.getUserEntity());
        if (!role.equals("OWNER")) {
            nameTrangTrai = entity.getTrangTraiEntity().getName();
        }

    }
}
