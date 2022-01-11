package com.alex.security.security;

import com.alex.common.consant.TokenConstant;
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

    public String createToken(String username) {
        long tokenExpiration = 24 * 60 * 60 * 1000;
        return Jwts.builder().setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .signWith(SignatureAlgorithm.HS512, TokenConstant.SECRET).compressWith(CompressionCodecs.GZIP).compact();
    }

    public String getUserFromToken(String token) {
        return Jwts.parser().setSigningKey(TokenConstant.SECRET).parseClaimsJws(token).getBody().getSubject();
    }

    public void removeToken(String token) {
        //jwt token无需删除，客户端扔掉即可。
    }

}
