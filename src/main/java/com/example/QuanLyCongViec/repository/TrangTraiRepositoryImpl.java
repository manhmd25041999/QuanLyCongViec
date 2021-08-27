package com.example.QuanLyCongViec.repository;


import com.example.QuanLyCongViec.base.StringTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class TrangTraiRepositoryImpl implements TrangTraiRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public long findByAccountId(long id) {
        StringTemplate queryTemplate = StringTemplate.from(
                "select b.trang_trai_id\n" +
                        "from trang_trai b\n" +
                        "         inner join account a on b.trang_trai_id = a.trang_trai_id\n" +
                        "where a.account_id = ${id}"
        );

        queryTemplate.setParam("id", id);
        Query query = entityManager.createNativeQuery(queryTemplate.build());
        List<Integer> idTrangTrai = query.getResultList();
        return (long) idTrangTrai.get(0);

    }
}
