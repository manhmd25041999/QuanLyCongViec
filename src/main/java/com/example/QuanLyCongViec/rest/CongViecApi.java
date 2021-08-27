package com.example.QuanLyCongViec.rest;


import com.example.QuanLyCongViec.base.BaseResource;
import com.example.QuanLyCongViec.dto.request.CreateCongViecDto;
import com.example.QuanLyCongViec.dto.response.CongViecDto;
import com.example.QuanLyCongViec.entity.CongViecEntity;
import com.example.QuanLyCongViec.exception.BadRequestException;
import com.example.QuanLyCongViec.service.CongViecService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/congviec")
public class CongViecApi extends BaseResource<CongViecService> {

    @PostMapping("/createAndUpdate")
    public CustomResponse<CongViecDto> createAndUpdate(@RequestBody CreateCongViecDto createCongViecDto, HttpServletResponse response) throws ParseException {
        this.checkRole(response, "MANAGER");
        CongViecEntity congViecEntity = this.service.createAndUpdate(createCongViecDto);
        return CustomResponse.ok(new CongViecDto(congViecEntity));
    }

    @GetMapping("getAllCongViec")
    public CustomResponse<List<CongViecDto>> getCongViec(HttpServletResponse response) {
        this.checkRole(response, "MANAGER");
        return CustomResponse.ok(this.service.getAll().stream().map(CongViecDto::new)
                .collect(Collectors.toList()));
    }

    @DeleteMapping
    public CustomResponse<String> delete(@RequestParam String code, HttpServletResponse response) {
        this.checkRole(response, "MANAGER");
        this.service.delete(code);
        return CustomResponse.ok("remove success!");
    }

    private void checkRole(HttpServletResponse response, String... roles) {
        String role = response.getHeader("ACCOUNT_ROLE");
        if (!Arrays.asList(roles).contains(role)) {
            throw new BadRequestException("chỉ có Manager mới có quyền truy cập");
        }
    }
}
