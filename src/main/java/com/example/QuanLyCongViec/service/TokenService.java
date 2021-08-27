package com.example.QuanLyCongViec.service;

import com.example.QuanLyCongViec.base.BaseService;
import com.example.QuanLyCongViec.base.UuidUtils;
import com.example.QuanLyCongViec.entity.AccountEntity;
import com.example.QuanLyCongViec.entity.UserEntity;
import com.example.QuanLyCongViec.repository.AccountRepository;
import com.example.QuanLyCongViec.repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

@Service
public class TokenService extends BaseService<AccountEntity, AccountRepository> {

    String key = "key gi cung duoc";

    public String genToken(UUID code, String username, String role) {
        long expired = LocalDateTime.now().plusMonths(1).
                atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        Claims claims = Jwts.claims();
        claims.setExpiration(new Date(expired));
        claims.setIssuer("Quan ly cong viec");
        claims.put("code", code);
        claims.put("username", username);
        claims.put("role", role);
        String token = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, key).compact();
        return token;
    }

    public AccountEntity verifyToken(String token){
        try{
            Claims body=Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
            UUID code = UuidUtils.getUuidFromStringOrElseThrowError(body.get("code").toString());
            return this.repository.findByCode(code);
        }catch (Exception ex){
            System.out.println("loi parse token "+ ex.getMessage());
            return null;
        }
    }

    public String verifyTokenToCode(String token){
        try{
            Claims body=Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
            return body.get("code").toString();
        }catch (Exception ex){
            System.out.println("loi parse token "+ ex.getMessage());
            return null;
        }
    }
}
