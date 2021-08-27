package com.example.QuanLyCongViec.base;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public abstract class BaseService<E extends BaseEntity, R extends BaseRepository<E>> {
    @Autowired
    protected R repository;

    public E createAndSave(E entity) {
        if (entity == null) {
            return null;
        }

        preSave(entity);
        return repository.save(entity);
    }

    public List<E> createAndSaveAll(List<E> entities) {
        for (E entity : entities) {
            preSave(entity);
        }
        return repository.saveAll(entities);
    }

    private void preSave(E entity) {
        // gen UUID code
        entity.setCode(UUID.randomUUID());

        entity.setCreatedTime(new Date());

//        CustomUserDetail currentUserLogin = SecurityUtils.getCurrentUserLogin();
//        if (currentUserLogin != null) {
//            entity.setCreatedByUserId(currentUserLogin.getUserId());
//        }
    }

    public E findById(Long id) {
        if (id == null) return null;
        return repository.findById(id).orElse(null);
    }

    public E findByCode(UUID code) {
        return repository.findByCode(code);
    }

    public List<E> findAllNotDeleted() {
        return repository.findByIsDeletedFalse();
    }

    public Page<E> findAllNotDeleted(Pageable page) {
        return repository.findByIsDeletedFalse(page);
    }

    public E update(E entity) {
        if (entity == null || entity.getId() == null) {
            return null;
        } else {
//            CustomUserDetail currentUserLogin = SecurityUtils.getCurrentUserLogin();
//            if (currentUserLogin != null) {
//                entity.setUpdatedByUserId(currentUserLogin.getUserId());
//            }

            entity.setUpdatedTime(new Date());
            return repository.save(entity);
        }
    }

    public void updateAll(List<E> entities) {
//        CustomUserDetail currentUserLogin = SecurityUtils.getCurrentUserLogin();

        entities.forEach(e -> {
//            if (currentUserLogin != null) {
//                e.setUpdatedByUserId(currentUserLogin.getUserId());
//            }

            e.setUpdatedTime(new Date());
        });
        repository.saveAll(entities);
    }

    public E updateAndFlush(E entity) {
        if (entity == null || entity.getId() == null) {
            return null;
        } else {
            entity.setUpdatedTime(new Date());
            return repository.saveAndFlush(entity);
        }
    }

    public boolean delete(UUID code) {
        //logic delete
        E entity = repository.findByCode(code);
        return delete(entity);
    }

    public boolean delete(E entity) {
        //logic delete
        if (entity == null) {
            return false;
        } else {
//            CustomUserDetail currentUserLogin = SecurityUtils.getCurrentUserLogin();
//            if (currentUserLogin != null) {
//                entity.setUpdatedByUserId(currentUserLogin.getUserId());
//            }

            entity.setUpdatedTime(new Date());
            entity.delete();
            repository.save(entity);
            return true;
        }
    }

    public void deleteAll(List<E> entities) {
//        CustomUserDetail currentUserLogin = SecurityUtils.getCurrentUserLogin();
//        Long deleter = currentUserLogin != null ? currentUserLogin.getUserId() : null;
        entities.forEach(entity -> {
            if (entity != null) {
//                if (deleter != null) {
//                    entity.setUpdatedByUserId(deleter);
//                }
                entity.setUpdatedTime(new Date());
                entity.delete();
                repository.save(entity);
            }
        });
    }
}
