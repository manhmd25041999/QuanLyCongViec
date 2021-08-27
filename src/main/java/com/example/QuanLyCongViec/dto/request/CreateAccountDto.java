package com.example.QuanLyCongViec.dto.request;


import com.example.QuanLyCongViec.enums.Role;
import lombok.Getter;

@Getter
public class CreateAccountDto {

    private String code;

    private String username;

    private String password;

    private String codeTrangTrai;

    private Role role;

    private CreateUserDto createUserDto;
}
