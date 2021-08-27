package com.example.QuanLyCongViec.rest;

import com.example.QuanLyCongViec.base.BaseResource;
import com.example.QuanLyCongViec.dto.request.CreateTrangTraiDto;
import com.example.QuanLyCongViec.dto.response.KhuVucDto;
import com.example.QuanLyCongViec.dto.response.TrangTraiDto;
import com.example.QuanLyCongViec.exception.BadRequestException;
import com.example.QuanLyCongViec.service.BangPhanCongService;
import com.example.QuanLyCongViec.service.KhuVucService;
import com.example.QuanLyCongViec.service.TrangTraiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/trangTrai")
public class TrangTraiApi extends BaseResource<TrangTraiService> {

    @Autowired
    private KhuVucService khuVucService;

    @Autowired
    private BangPhanCongService bangPhanCongService;

    @PostMapping("/createAndUpdate")
    public CustomResponse<TrangTraiDto> createAndUpdate(@RequestBody CreateTrangTraiDto createTrangTraiDto, HttpServletResponse response) {
        this.checkRole(response, "OWNER");
        return CustomResponse.ok(new TrangTraiDto(service.createAndUpdate(createTrangTraiDto), null));
    }

    @GetMapping
    public CustomResponse<List<TrangTraiDto>> getAll(HttpServletResponse response) {
        this.checkRole(response, "OWNER", "MANAGER");
        String code = response.getHeader("ACCOUNT_CODE");
        return CustomResponse.ok(this.service.getAll(code).stream().map(trangTraiEntity -> new TrangTraiDto(trangTraiEntity
                , khuVucService.findByAccount(null, trangTraiEntity.getId()).stream().map(a-> new KhuVucDto(a, bangPhanCongService.findByKhuVuc(a.getId()))).collect(Collectors.toList())))
                .collect(Collectors.toList()));
    }

    private void checkRole(HttpServletResponse response, String... roles) {
        String role = response.getHeader("ACCOUNT_ROLE");
        if (!Arrays.asList(roles).contains(role) && roles.length == 1) {
            throw new BadRequestException("chỉ có OWNER mới có quyền truy cập");
        }else if (!Arrays.asList(roles).contains(role) && roles.length == 2)
            throw new BadRequestException("chỉ có OWNER và MANAGER mới có quyền truy cập");
    }


}
