package com.example.QuanLyCongViec.entity;

import com.example.QuanLyCongViec.base.BaseEntity;
import com.example.QuanLyCongViec.dto.request.CreateCongViecDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@Table(name = "cong_viec")
@Entity
@Where(clause = "is_deleted = false")
@AttributeOverride(column = @Column(name = "cong_viec_id"), name = "id")
public class CongViecEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "deadline")
    private Date deadline;


    public CongViecEntity(String name, String description, String deadline) throws ParseException {
        this.name = name;
        this.description = description;

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.deadline = dateFormat.parse(deadline);
    }

    public void update(CreateCongViecDto createCongViecDto) throws ParseException {
        this.name = createCongViecDto.getName();
        this.description = createCongViecDto.getDescription();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.deadline = dateFormat.parse(createCongViecDto.getDeadline());
    }
}
