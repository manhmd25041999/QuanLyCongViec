package com.example.QuanLyCongViec.repository;

import com.example.QuanLyCongViec.base.BaseRepository;
import com.example.QuanLyCongViec.entity.KhuVucEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface KhuVucRepository extends BaseRepository<KhuVucEntity> {

    List<KhuVucEntity> findByTrangTraiEntity_Id(long id);
}
