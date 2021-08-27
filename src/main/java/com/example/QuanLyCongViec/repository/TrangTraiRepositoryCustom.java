package com.example.QuanLyCongViec.repository;


import org.springframework.stereotype.Repository;



@Repository
public interface TrangTraiRepositoryCustom {

    long findByAccountId(long id);


}
