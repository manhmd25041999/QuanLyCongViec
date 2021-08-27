package com.example.QuanLyCongViec.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = 4616401340104536083L;

    //Backend only identifier
    //Generate in sequence, do NOT send to frontend
    @Id
    //@Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    protected Long id;

    //Frontend identifier
    //unique, non-sequence generate, may send to frontend
    protected UUID code;

    @JsonIgnore
    protected Date createdTime;
    @JsonIgnore
    protected Date updatedTime;

//    @JsonIgnore
//    protected Long createdByUserId;
//    @JsonIgnore
//    protected Long updatedByUserId;

    @JsonIgnore
    @Setter(AccessLevel.NONE)
    protected boolean isDeleted;

    public final void delete() {
        isDeleted = true;
    }

    public boolean notDeleted() {
        return !isDeleted;
    }
}

