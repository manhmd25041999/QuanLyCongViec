package com.example.QuanLyCongViec.service;

import com.example.QuanLyCongViec.base.BaseService;
import com.example.QuanLyCongViec.base.UuidUtils;
import com.example.QuanLyCongViec.dto.request.CreateAccountDto;
import com.example.QuanLyCongViec.dto.response.AccountDto;
import com.example.QuanLyCongViec.entity.AccountEntity;
import com.example.QuanLyCongViec.entity.BangPhanCongEntity;
import com.example.QuanLyCongViec.entity.TrangTraiEntity;
import com.example.QuanLyCongViec.entity.UserEntity;
import com.example.QuanLyCongViec.exception.BadRequestException;
import com.example.QuanLyCongViec.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AccountService extends BaseService<AccountEntity, AccountRepository> {

    @Autowired
    private UserService userService;

    @Autowired
    private TrangTraiService trangTraiService;

    public AccountEntity created(CreateAccountDto createAccountDto) {
        boolean createNew = createAccountDto.getCode() == null;

        TrangTraiEntity trangTraiEntity = null;
        if (!createAccountDto.getRole().getName().equals("OWNER")) {
            trangTraiEntity = trangTraiService.findByCode(UuidUtils.getUuidFromStringOrElseThrowError(createAccountDto.getCodeTrangTrai()));
        }

        if (createNew) {
            UserEntity userEntity = userService.createUser(createAccountDto.getCreateUserDto());
            if (this.repository.countByUsername(createAccountDto.getUsername()) != 0) {
                throw new BadRequestException("Username was used!");
            }

            return createAndSave(new AccountEntity(createAccountDto.getUsername(), createAccountDto.getPassword(),
                    createAccountDto.getRole().getName(), userEntity, trangTraiEntity));
        } else {
            UUID uuid = UuidUtils.getUuidFromStringOrElseThrowError(createAccountDto.getCode());
            AccountEntity accountEntity = findByCode(uuid);
            accountEntity.update(createAccountDto, trangTraiEntity);
            userService.update(accountEntity.getUserEntity());
            return this.update(accountEntity);
        }

    }

    public AccountEntity login(String username, String pass) {
        return this.repository.findByUsernameAndPassword(username, pass);
    }

    public boolean delete(String code) {
        UUID uuid = UuidUtils.getUuidFromStringOrElseThrowError(code);
        boolean delete = userService.delete(findByCode(uuid).getUserEntity());
        if (delete) {
            return this.delete(uuid);
        }
        throw new BadRequestException("remove fail!");
    }

    public List<AccountEntity> getAll() {
        return repository.findByIsDeletedFalse();
    }


}
