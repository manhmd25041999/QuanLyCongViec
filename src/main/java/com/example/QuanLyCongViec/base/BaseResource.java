package com.example.QuanLyCongViec.base;

import org.springframework.beans.factory.annotation.Autowired;

public class BaseResource<S extends BaseService<? extends BaseEntity, ? extends BaseRepository<? extends BaseEntity>>> {
    @Autowired
    protected S service;
}
