package com.example.QuanLyCongViec.filter;


import com.example.QuanLyCongViec.entity.AccountEntity;
import com.example.QuanLyCongViec.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class TokenFilter implements Filter {

    @Autowired
    private TokenService tokenService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        if (request.getServletPath().contains("token/login") || request.getServletPath().contains("token/create")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }


        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String token = request.getHeader("Authorization");
        token = token.substring(7);

        if (StringUtils.isEmpty(token)) {
            this.outFailKhiTokenCoVanDeHehehehe(response, "phai login");
            return;
        }

        AccountEntity accountEntity = tokenService.verifyToken(token);
        if (accountEntity == null) {
            this.outFailKhiTokenCoVanDeHehehehe(response, "token sai");
            return;
        }

//        if(!(user.getRole()).equals("ADMIN")){
//            this.outFailKhiTokenCoVanDeHehehehe(response,"chi co admin moi xem duoc danh sach nhan vien!");
//            return;
//        }


        response.addHeader("ACCOUNT_ROLE", accountEntity.getRole() + "");
        response.addHeader("ACCOUNT_CODE", tokenService.verifyTokenToCode(token));

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void outFailKhiTokenCoVanDeHehehehe(HttpServletResponse servletResponse, String msg) throws IOException {
        HttpServletResponse res = servletResponse;
        res.setStatus(401);
        res.setContentType(MediaType.APPLICATION_JSON_VALUE);
        PrintWriter out = res.getWriter();
        out.write("{\n" +
                "    \"loi\": \"" + msg + "\"\n" +
                "}");
        out.flush();
    }
}
