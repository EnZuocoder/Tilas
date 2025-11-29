package com.hfut.tilaswebmangement;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {
    static String jwtToken;
    public static void testJwt()
    {
        //密钥，至少需要256位长度
        String secret = "wcxy_2024_hfut_tilas_web_mangement_secret";
        //key是用来签名和验证签名的
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        Map<String, Object> claims = new HashMap<>();
        //这是自定义的载荷信息
        claims.put("userId", 123);
        claims.put("role", "admin");

        long now = System.currentTimeMillis();
        jwtToken= Jwts.builder()
                .setClaims(claims)
                .setSubject("EnZuocoder")
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + 3600_000)) // 1 小时后过期
                .signWith(key, SignatureAlgorithm.HS256)//设置签名算法和密钥
                .compact();//生成令牌
        System.out.println(jwtToken);
    }
    @Test
    public void testParseJwt() {
        testJwt();
            //解析给定jwt令牌jwtToken
        String secret = "wcxy_2024_hfut_tilas_web_mangement_secret";//解析需要对应密钥
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
        System.out.println(claims);


    }

}
