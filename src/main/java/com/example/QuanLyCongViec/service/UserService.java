package com.example.QuanLyCongViec.service;

import com.example.QuanLyCongViec.base.BaseService;
import com.example.QuanLyCongViec.dto.request.CreateUserDto;
import com.example.QuanLyCongViec.entity.UserEntity;
import com.example.QuanLyCongViec.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class UserService extends BaseService<UserEntity, UserRepository> {


    public UserEntity createUser(CreateUserDto createUserDto){
        return createAndSave(new UserEntity(createUserDto.getName(), createUserDto.getAddress(), createUserDto.getAge()));
    }





}
