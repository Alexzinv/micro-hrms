package com.alex.serurity.security;

import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * token管理
 * @author _
 */
@Component
public class TokenManager {

    private final String tokenSignKey = "123456";

    public String createToken(String username) {
        long tokenExpiration = 24 * 60 * 60 * 1000;
        return Jwts.builder().setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .signWith(SignatureAlgorithm.HS512, tokenSignKey).compressWith(CompressionCodecs.GZIP).compact();
    }

    public String getUserFromToken(String token) {
        return Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token).getBody().getSubject();
    }

    public void removeToken(String token) {
        //jwt token无需删除，客户端扔掉即可。
    }

}
