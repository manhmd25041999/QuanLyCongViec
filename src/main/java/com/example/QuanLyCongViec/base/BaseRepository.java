package com.example.QuanLyCongViec.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.UUID;

@NoRepositoryBean
public interface BaseRepository<E extends BaseEntity> extends JpaRepository<E, Long> {
    E findByCode(UUID code);
    List<E> findByCodeIn(List<UUID> code);

    List<E> findByIsDeletedFalse();
    Page<E> findByIsDeletedFalse(Pageable page);
}
