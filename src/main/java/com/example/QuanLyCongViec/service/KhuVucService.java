package com.example.QuanLyCongViec.service;

import com.example.QuanLyCongViec.base.BaseService;
import com.example.QuanLyCongViec.base.UuidUtils;
import com.example.QuanLyCongViec.dto.request.CreateKhuVucDto;
import com.example.QuanLyCongViec.entity.AccountEntity;
import com.example.QuanLyCongViec.entity.KhuVucEntity;
import com.example.QuanLyCongViec.entity.TrangTraiEntity;
import com.example.QuanLyCongViec.repository.KhuVucRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KhuVucService extends BaseService<KhuVucEntity, KhuVucRepository> {

    @Autowired
    private TrangTraiService trangTraiService;

    @Autowired
    private AccountService accountService;

    public KhuVucEntity createAndUpdate(CreateKhuVucDto createKhuVucDto) {
        boolean createNew = createKhuVucDto.getCode() == null;
        if (createNew) {
            TrangTraiEntity trangTraiEntity = trangTraiService.findByCode(UuidUtils
                    .getUuidFromStringOrElseThrowError(createKhuVucDto.getCodeTrangTrai()));
            return createAndSave(new KhuVucEntity(createKhuVucDto.getName(), trangTraiEntity));
        } else {
            KhuVucEntity khuVucEntity = findByCode(UuidUtils
                    .getUuidFromStringOrElseThrowError(createKhuVucDto.getCode()));
            khuVucEntity.setName(createKhuVucDto.getName());
            return update(khuVucEntity);
        }
    }


    public List<KhuVucEntity> findByAccount(String code, Long idTrangTrai) {
        if (idTrangTrai != null) {
            return this.repository.findByTrangTraiEntity_Id(idTrangTrai);
        }
        AccountEntity accountEntity = accountService.findByCode(UuidUtils.getUuidFromStringOrElseThrowError(code));
        if (accountEntity.getRole().equals("OWNER")) {
            return this.repository.findByIsDeletedFalse();
        } else {
            return this.repository.findByTrangTraiEntity_Id(accountEntity.getTrangTraiEntity().getId());
        }
    }


}
