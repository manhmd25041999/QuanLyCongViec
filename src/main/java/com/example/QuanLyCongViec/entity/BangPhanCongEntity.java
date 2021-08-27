package com.example.QuanLyCongViec.entity;

import com.example.QuanLyCongViec.base.BaseEntity;
import com.example.QuanLyCongViec.enums.DanhGia;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Table(name = "bang_phan_cong")
@Entity
@Where(clause = "is_deleted = false")
@AttributeOverride(column = @Column(name = "bang_phan_cong_id"), name = "id")
public class BangPhanCongEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "cong_viec_id")
    private CongViecEntity congViecEntity;

    @ManyToOne
    @JoinColumn(name = "account_id")
    AccountEntity accountEntitie;

    @ManyToOne
    @JoinColumn(name = "khu_vuc_id")
    private KhuVucEntity khuVucEntity;

    @Column(name = "danh_gia")
    private String danhGia;

    public BangPhanCongEntity(CongViecEntity congViecEntity, AccountEntity accountEntitie, KhuVucEntity khuVucEntity) {
        this.congViecEntity = congViecEntity;
        this.accountEntitie = accountEntitie;
        this.khuVucEntity = khuVucEntity;
        this.danhGia = DanhGia.CHUAHOANTHANH.getName();
    }
}
