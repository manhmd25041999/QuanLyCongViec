package com.example.QuanLyCongViec.entity;

import com.example.QuanLyCongViec.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Table(name = "khu_vuc")
@Entity
@Where(clause = "is_deleted = false")
@AttributeOverride(column = @Column(name = "khu_vuc_id"), name = "id")
public class KhuVucEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "trang_trai_id")
    private TrangTraiEntity trangTraiEntity;

//    @OneToMany(mappedBy = "khuVucEntity", fetch = FetchType.EAGER)
//    private List<BangPhanCongEntity> bangPhanCongEntities;


    public KhuVucEntity(String name, TrangTraiEntity trangTraiEntity) {
        this.name = name;
        this.trangTraiEntity = trangTraiEntity;
    }
}
