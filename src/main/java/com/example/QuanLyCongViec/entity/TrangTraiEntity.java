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
@Table(name = "trang_trai")
@Entity
@Where(clause = "is_deleted = false")
@AttributeOverride(column = @Column(name = "trang_trai_id"), name = "id")
public class TrangTraiEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "trangTraiEntity", fetch = FetchType.EAGER)
    List<AccountEntity> accountEntities;

    //    @Transient
//    @OneToMany(mappedBy = "trangTrai", fetch = FetchType.LAZY)
//    List<BangPhanCongEntity> bangPhanCongEntities;

    public TrangTraiEntity(String name) {
        this.name = name;
    }
}
