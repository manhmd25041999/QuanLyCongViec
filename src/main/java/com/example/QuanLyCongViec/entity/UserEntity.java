package com.example.QuanLyCongViec.entity;


import com.example.QuanLyCongViec.base.BaseEntity;

import com.example.QuanLyCongViec.dto.request.CreateUserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "user")
@Where(clause = "is_deleted = false")
@AttributeOverride(column = @Column(name = "user_id"), name = "id")
public class UserEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "age")
    private int age;

    public UserEntity(String name, String address, int age) {
        this.name = name;
        this.address = address;
        this.age = age;
    }


}
