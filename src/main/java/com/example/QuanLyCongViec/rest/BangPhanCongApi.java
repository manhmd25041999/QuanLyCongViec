package com.example.QuanLyCongViec.rest;

import com.example.QuanLyCongViec.base.BaseResource;
import com.example.QuanLyCongViec.dto.request.CreateBangPhanCongDto;
import com.example.QuanLyCongViec.dto.response.BangPhanCongDto;
import com.example.QuanLyCongViec.enums.DanhGia;
import com.example.QuanLyCongViec.exception.BadRequestException;
import com.example.QuanLyCongViec.service.BangPhanCongService;
import org.apache.catalina.LifecycleState;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/bangPhanCong")
public class BangPhanCongApi extends BaseResource<BangPhanCongService> {

    @PostMapping("/createAndUpdate")
    public CustomResponse<BangPhanCongDto> createAndUpdate(@RequestBody CreateBangPhanCongDto createBangPhanCongDto, HttpServletResponse response) {
        this.checkRole(response, "MANAGER");
        String code=response.getHeader("ACCOUNT_CODE");
        return CustomResponse.ok(this.service.createAndUpdate(createBangPhanCongDto, code));
    }

    @DeleteMapping("/delete")
    public CustomResponse<String> delete(@RequestParam String CongViecCode, HttpServletResponse response) {
        this.checkRole(response, "MANAGER");
        this.service.delete(CongViecCode);
        return CustomResponse.ok("remove success!");
    }

    @PostMapping("/updateTrangThai")
    public CustomResponse<BangPhanCongDto> updateTrangThai(@RequestParam String CongViecCode, DanhGia danhGia, HttpServletResponse response) {
        this.checkRole(response, "MANAGER");
        return CustomResponse.ok(this.service.updateTrangThai(CongViecCode, danhGia));
    }

    @GetMapping("/getByAccount")
    public CustomResponse<List<BangPhanCongDto>> findByAccount(HttpServletResponse response) {
        String code = response.getHeader("ACCOUNT_CODE");
        return CustomResponse.ok(this.service.findByAccount(code));
    }

    @GetMapping("/canhBao")
    public CustomResponse<List<BangPhanCongDto>> canhBao(HttpServletResponse response) {
        String code = response.getHeader("ACCOUNT_CODE");
        return CustomResponse.ok(this.service.CanhBao(code));
    }

    private void checkRole(HttpServletResponse response, String... roles) {
        String role = response.getHeader("ACCOUNT_ROLE");
        if (!Arrays.asList(roles).contains(role)) {
            throw new BadRequestException("chỉ có Manager mới có quyền truy cập");
        }
    }


}
