package com.example.QuanLyCongViec.repository;

import com.example.QuanLyCongViec.base.BaseRepository;
import com.example.QuanLyCongViec.entity.AccountEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AccountRepository extends BaseRepository<AccountEntity> {
    long countByUsername(String name);

    AccountEntity findByUsernameAndPassword(String username, String pass);


}
