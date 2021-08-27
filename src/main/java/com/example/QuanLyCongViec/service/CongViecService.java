package com.example.QuanLyCongViec.service;

import com.example.QuanLyCongViec.base.BaseService;
import com.example.QuanLyCongViec.base.UuidUtils;
import com.example.QuanLyCongViec.dto.request.CreateCongViecDto;
import com.example.QuanLyCongViec.entity.CongViecEntity;
import com.example.QuanLyCongViec.repository.CongViecRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.UUID;

@Service
public class CongViecService extends BaseService<CongViecEntity, CongViecRepository> {

    public CongViecEntity createAndUpdate(CreateCongViecDto createCongViecDto) throws ParseException {
        boolean createNew = createCongViecDto.getCode() == null;
        CongViecEntity congViecEntity;
        if (createNew) {
            congViecEntity = new CongViecEntity(createCongViecDto.getName(),
                    createCongViecDto.getDescription(), createCongViecDto.getDeadline());
            return createAndSave(congViecEntity);
        }else{
            UUID code = UuidUtils.getUuidFromStringOrElseThrowError(createCongViecDto.getCode());
            congViecEntity= findByCode(code);
            congViecEntity.update(createCongViecDto);
            return update(congViecEntity);
        }
    }

    public List<CongViecEntity> getAll(){
        return this.repository.findByIsDeletedFalse();
    }

    public void delete(String code){
        UUID uuid = UuidUtils.getUuidFromStringOrElseThrowError(code);
        delete(uuid);
    }

}
