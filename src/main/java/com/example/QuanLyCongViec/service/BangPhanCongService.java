package com.example.QuanLyCongViec.service;

import com.example.QuanLyCongViec.base.BaseService;
import com.example.QuanLyCongViec.base.UuidUtils;
import com.example.QuanLyCongViec.dto.request.CreateBangPhanCongDto;
import com.example.QuanLyCongViec.dto.response.BangPhanCongDto;
import com.example.QuanLyCongViec.entity.*;
import com.example.QuanLyCongViec.enums.DanhGia;
import com.example.QuanLyCongViec.exception.BadRequestException;
import com.example.QuanLyCongViec.repository.BangPhanCongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BangPhanCongService extends BaseService<BangPhanCongEntity, BangPhanCongRepository> {

    @Autowired
    private CongViecService congViecService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TrangTraiService trangTraiService;

    @Autowired
    private KhuVucService khuVucService;

    public BangPhanCongDto createAndUpdate(CreateBangPhanCongDto createBangPhanCongDto, String code) {


        List<BangPhanCongEntity> bangPhanCongEntities;
        boolean createNew = createBangPhanCongDto.getCode() == null;
        if (createNew) {

            bangPhanCongEntities = createAndSaveAll(create(createBangPhanCongDto, code));

            return new BangPhanCongDto(bangPhanCongEntities.get(0)
                    , bangPhanCongEntities.stream()
                    .map(a -> accountService.findById(a.getAccountEntitie().getId()))
                    .collect(Collectors.toList()));


        } else {

            BangPhanCongEntity bangPhanCongEntity = findByCode(UuidUtils.getUuidFromStringOrElseThrowError(createBangPhanCongDto.getCode()));

            bangPhanCongEntities = repository.findByCongViecEntity_Code(UuidUtils.getUuidFromStringOrElseThrowError(createBangPhanCongDto.getCodeCongViec()));
            if (bangPhanCongEntities.size() == 0) {
                throw new BadRequestException("code cong viec sai!");
            }
            repository.deleteAll(bangPhanCongEntities);

            bangPhanCongEntities = create(createBangPhanCongDto, code);
            bangPhanCongEntities.stream().forEach(bangPhanCongEntity1 -> bangPhanCongEntity1.setDanhGia(bangPhanCongEntity.getDanhGia()));
            bangPhanCongEntities = createAndSaveAll(bangPhanCongEntities);

            return new BangPhanCongDto(bangPhanCongEntities.get(0)
                    , bangPhanCongEntities.stream()
                    .map(a -> accountService.findById(a.getAccountEntitie().getId()))
                    .collect(Collectors.toList()));

        }
    }

    public List<BangPhanCongEntity> create(CreateBangPhanCongDto createBangPhanCongDto, String code) {
        AccountEntity accountEntity1 = accountService.findByCode(UuidUtils.getUuidFromStringOrElseThrowError(code));
        List<BangPhanCongEntity> bangPhanCongEntities = new ArrayList<>();
        CongViecEntity congViecEntity = congViecService.findByCode(UuidUtils
                .getUuidFromStringOrElseThrowError(createBangPhanCongDto.getCodeCongViec()));

        KhuVucEntity khuVucEntity = khuVucService.findByCode(UuidUtils.getUuidFromStringOrElseThrowError(createBangPhanCongDto.getCodeKhuVuc()));

        TrangTraiEntity trangTraiEntity = khuVucEntity.getTrangTraiEntity();

        if (!trangTraiEntity.getId().equals(accountEntity1.getTrangTraiEntity().getId())) {
            throw new BadRequestException("manager " + accountEntity1.getUsername() + " khong phai la manager cua trang trai " + trangTraiEntity.getName());
        }

        for (String codeAccount : createBangPhanCongDto.getCodeAccountEmployee()) {
            AccountEntity accountEntity = accountService.findByCode(UuidUtils.getUuidFromStringOrElseThrowError(codeAccount));
            if (accountEntity.getTrangTraiEntity().getId() != trangTraiEntity.getId()) {
                throw new BadRequestException("employee " + accountEntity.getUsername()
                        + " khong thuoc trang trai " + trangTraiEntity.getName());
            }
            bangPhanCongEntities.add(new BangPhanCongEntity(congViecEntity, accountEntity, khuVucEntity));
        }
        return bangPhanCongEntities;
    }


    public List<BangPhanCongDto> getAll() {
        return findBy(null, null, null);
    }

    public void delete(String codeCongViec) {
        UUID code = UuidUtils.getUuidFromStringOrElseThrowError(codeCongViec);
        List<BangPhanCongEntity> bangPhanCongEntities = repository.findByCongViecEntity_Code(code);
        deleteAll(bangPhanCongEntities);
    }

    public BangPhanCongDto updateTrangThai(String congViecCode, DanhGia danhGia) {
        UUID code = UuidUtils.getUuidFromStringOrElseThrowError(congViecCode);
        List<BangPhanCongEntity> bangPhanCongEntities = repository.findByCongViecEntity_Code(code);
        bangPhanCongEntities.forEach(a -> a.setDanhGia(danhGia.getName()));
        updateAll(bangPhanCongEntities);

        return new BangPhanCongDto(bangPhanCongEntities.get(0)
                , bangPhanCongEntities.stream()
                .map(a -> accountService.findById(a.getAccountEntitie().getId()))
                .collect(Collectors.toList()));
    }

    public List<BangPhanCongDto> findByAccount(String code) {
        UUID uuid = UuidUtils.getUuidFromStringOrElseThrowError(code);
        AccountEntity accountEntity = accountService.findByCode(uuid);
        if (accountEntity.getRole().equals("OWNER")) {
            return getAll();
        }
        if (accountEntity.getRole().equals("MANAGER")) {
            return findByTrangTrai(accountEntity.getTrangTraiEntity().getId());
        }
        return findBy(uuid, null, null);
    }

    public List<BangPhanCongDto> findByTrangTrai(long id) {
        return findBy(null, id, null);
    }

    public List<BangPhanCongDto> findBy(UUID uuid, Long idTrangTrai, Long idKhuVuc) {
        List<Long> congViecIdList = this.repository.groupByCongViecId();

        List<BangPhanCongDto> bangPhanCongDtos = new ArrayList<>();

        List<BangPhanCongEntity> bangPhanCongEntities;

        for (Long id : congViecIdList) {
            if (uuid != null) {
                bangPhanCongEntities = repository.findByAccountEntitie_CodeAndCongViecEntity_Id(uuid, id);
            } else if (idTrangTrai != null) {
                bangPhanCongEntities = repository.findByKhuVucEntity_TrangTraiEntity_IdAndCongViecEntity_Id(idTrangTrai, id);
            }else if (idKhuVuc != null){
                bangPhanCongEntities=repository.findByKhuVucEntity_IdAndCongViecEntity_Id(idKhuVuc, id);
            }
            else {
                bangPhanCongEntities = repository.findByCongViecEntity_Id(id);
            }

            if (bangPhanCongEntities.size() != 0) {
                bangPhanCongDtos.add(new BangPhanCongDto(bangPhanCongEntities.get(0)
                        , bangPhanCongEntities.stream()
                        .map(bangPhanCongEntity -> accountService.findById(bangPhanCongEntity.getAccountEntitie().getId()))
                        .collect(Collectors.toList())));
            }

        }

        return bangPhanCongDtos;
    }

    public List<BangPhanCongDto> CanhBao(String code) {
        return findByAccount(code).stream().filter(bangPhanCongDto -> bangPhanCongDto.getTrangThai().equals("Qua han")).collect(Collectors.toList());
    }

    public List<BangPhanCongDto> findByKhuVuc(long id){
        return findBy(null, null, id);
    }

}
