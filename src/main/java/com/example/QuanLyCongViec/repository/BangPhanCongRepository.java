package com.example.QuanLyCongViec.repository;

import com.example.QuanLyCongViec.base.BaseRepository;
import com.example.QuanLyCongViec.entity.BangPhanCongEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BangPhanCongRepository extends BaseRepository<BangPhanCongEntity> {

    List<BangPhanCongEntity> findByCongViecEntity_Code(UUID code);

    @Query("select a.congViecEntity.id from BangPhanCongEntity a group by a.congViecEntity.id")
    List<Long> groupByCongViecId();

    List<BangPhanCongEntity> findByCongViecEntity_Id(long id);

    List<BangPhanCongEntity> findByAccountEntitie_CodeAndCongViecEntity_Id(UUID code, long id);

    List<BangPhanCongEntity> findByKhuVucEntity_TrangTraiEntity_IdAndCongViecEntity_Id(long idTrangTrai, long idCongViec);

    List<BangPhanCongEntity> findByKhuVucEntity_IdAndCongViecEntity_Id(long idKhuVuc, long idCongViec);
}
