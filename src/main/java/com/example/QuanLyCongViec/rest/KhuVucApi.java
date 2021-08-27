package com.example.QuanLyCongViec.rest;

import com.example.QuanLyCongViec.base.BaseResource;
import com.example.QuanLyCongViec.dto.request.CreateKhuVucDto;
import com.example.QuanLyCongViec.dto.response.KhuVucDto;
import com.example.QuanLyCongViec.exception.BadRequestException;
import com.example.QuanLyCongViec.service.BangPhanCongService;
import com.example.QuanLyCongViec.service.KhuVucService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/khu_vuc")
public class KhuVucApi extends BaseResource<KhuVucService> {

    @Autowired
    private BangPhanCongService bangPhanCongService;

    @PostMapping
    public CustomResponse<KhuVucDto> createAndUpdate(@RequestBody CreateKhuVucDto createKhuVucDto){
        return CustomResponse.ok(new KhuVucDto(this.service.createAndUpdate(createKhuVucDto), null));
    }

    @GetMapping("/findByAccount")
    public CustomResponse<List<KhuVucDto>> findByAccount(HttpServletResponse response){
        if (!Arrays.asList("MANAGER","OWNER").contains(response.getHeader("ACCOUNT_ROLE"))) {
            throw new BadRequestException("chỉ có Manager và Owner mới có quyền truy cập");
        }
        String code=response.getHeader("ACCOUNT_CODE");
        return CustomResponse.ok(this.service.findByAccount(code, null).stream().map(a-> new KhuVucDto(a, bangPhanCongService.findByKhuVuc(a.getId()))).collect(Collectors.toList()));
    }


}
