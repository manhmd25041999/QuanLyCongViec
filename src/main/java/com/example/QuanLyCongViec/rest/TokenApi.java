package com.example.QuanLyCongViec.rest;


import com.example.QuanLyCongViec.base.BaseResource;
import com.example.QuanLyCongViec.dto.request.CreateAccountDto;
import com.example.QuanLyCongViec.dto.response.AccountDto;
import com.example.QuanLyCongViec.entity.AccountEntity;
import com.example.QuanLyCongViec.exception.BadRequestException;
import com.example.QuanLyCongViec.exception.LoginFailExceptionHehe;
import com.example.QuanLyCongViec.service.AccountService;
import com.example.QuanLyCongViec.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/token")
public class TokenApi extends BaseResource<AccountService> {

    @Autowired
    private TokenService tokenService;

    @PostMapping("/create")
    public CustomResponse<AccountDto> create(@RequestBody CreateAccountDto createAccountDto) {
        return CustomResponse.ok(new AccountDto(this.service.created(createAccountDto)));
    }

    @PostMapping("/login")
    public CustomResponse<?> login(@RequestParam String username,
                                   @RequestParam String password) {
        AccountEntity accountEntity = service.login(username, password);
        if (accountEntity == null) {
            throw new LoginFailExceptionHehe("login fail!");
        }
        String token = tokenService.genToken(accountEntity.getCode(),
                accountEntity.getUsername(), accountEntity.getRole());

        Map<String, Object> results = new HashMap<>();
        results.put("token", token);
        results.put("fullname", accountEntity.getUserEntity().getName());
        results.put("role", accountEntity.getRole());
        return CustomResponse.ok(results);
    }

    @PostMapping("/update")
    public CustomResponse<AccountDto> update(@RequestBody CreateAccountDto createAccountDto, HttpServletResponse response) {
        this.checkRole(response, "MANAGER", "EMPLOYEE", "OWNER");
        return CustomResponse.ok(new AccountDto(this.service.created(createAccountDto)));
    }

    @GetMapping
    public CustomResponse<List<AccountDto>> get(HttpServletResponse response) {
        this.checkRole(response, "MANAGER", "OWNER");
        return CustomResponse.ok(this.service.getAll().stream().map(AccountDto::new)
                .collect(Collectors.toList()));
    }

    @DeleteMapping
    public CustomResponse<String> delete(@RequestParam String code, HttpServletResponse response){
        this.checkRole(response, "MANAGER", "OWNER");
        this.service.delete(code);
        return CustomResponse.ok("remove success");
    }

    private void checkRole(HttpServletResponse response, String... roles) {
        String role = response.getHeader("ACCOUNT_ROLE");
        if (!Arrays.asList(roles).contains(role)) {
            throw new BadRequestException("chỉ có Manager và Owner mới có quyền truy cập");
        }
    }


}
