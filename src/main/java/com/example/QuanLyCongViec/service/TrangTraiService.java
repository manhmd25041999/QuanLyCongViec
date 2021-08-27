package com.example.QuanLyCongViec.service;

import com.example.QuanLyCongViec.base.BaseService;
import com.example.QuanLyCongViec.base.UuidUtils;
import com.example.QuanLyCongViec.dto.request.CreateTrangTraiDto;
import com.example.QuanLyCongViec.entity.AccountEntity;
import com.example.QuanLyCongViec.entity.TrangTraiEntity;
import com.example.QuanLyCongViec.repository.TrangTraiRepository;
import com.example.QuanLyCongViec.repository.TrangTraiRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class TrangTraiService extends BaseService<TrangTraiEntity, TrangTraiRepository> {

    @Autowired
    private AccountService accountService;


    @Autowired
    private TrangTraiRepositoryCustom trangTraiRepositoryCustoml;


    public TrangTraiEntity createAndUpdate(CreateTrangTraiDto createTrangTraiDto) {
        boolean createNew = createTrangTraiDto.getCode() == null;
        if (createNew) {
            return createAndSave(new TrangTraiEntity(createTrangTraiDto.getName()));
        } else {
            TrangTraiEntity trangTraiEntity = findByCode(UuidUtils.getUuidFromStringOrElseThrowError(createTrangTraiDto.getCode()));
            trangTraiEntity.setName(createTrangTraiDto.getName());
            return update(trangTraiEntity);
        }
    }

    public List<TrangTraiEntity> getAll(String code){
        UUID uuid = UuidUtils.getUuidFromStringOrElseThrowError(code);
        AccountEntity accountEntity = accountService.findByCode(uuid);
        if (accountEntity.getRole().equals("OWNER")){
            return repository.findByIsDeletedFalse();
        }
        return Arrays.asList(findById(trangTraiRepositoryCustoml.findByAccountId(accountEntity.getId())));
    }








}
