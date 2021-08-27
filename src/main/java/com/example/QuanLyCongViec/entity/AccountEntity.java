package com.example.QuanLyCongViec.entity;


import com.example.QuanLyCongViec.base.BaseEntity;
import com.example.QuanLyCongViec.dto.request.CreateAccountDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Table(name = "account")
@Entity
@Where(clause = "is_deleted = false")
@AttributeOverride(column = @Column(name = "account_id"), name = "id")
public class AccountEntity extends BaseEntity {

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;


    @ManyToOne
    @JoinColumn(name = "trang_trai_id")
    private TrangTraiEntity trangTraiEntity;

    public AccountEntity(String username, String password, String role, UserEntity userEntity, TrangTraiEntity trangTraiEntity) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.userEntity = userEntity;
        this.trangTraiEntity = trangTraiEntity;
    }

    public void update(CreateAccountDto createAccountDto, TrangTraiEntity trangTraiEntity) {
//        this.username = createAccountDto.getUsername();
        this.password = createAccountDto.getPassword();
        this.role = createAccountDto.getRole().getName();
        this.userEntity.setName(createAccountDto.getCreateUserDto().getName());
        this.userEntity.setAddress(createAccountDto.getCreateUserDto().getAddress());
        this.userEntity.setAge(createAccountDto.getCreateUserDto().getAge());
        this.trangTraiEntity =trangTraiEntity;
    }
}
