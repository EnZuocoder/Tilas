// java
package com.hfut.tilaswebmangement.utils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
public class JwtUtils {

    // 常量密钥（示例），请在生产环境使用更安全的随机密钥并妥善保管
    private static final String SECRET = "ThisIsA32BytePlusSecretKeyForHS25612345";
    private static final SecretKey KEY = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    private static final long DEFAULT_TTL_MILLIS = 3600_000L; // 1 hour

    /**
     * 生成 JWT（使用常量密钥），返回 compact 字符串
     * 使用示例：createToken(Map.of("userId", 1), "username")
     */
    public static String createToken(Map<String, Object> claims) {
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + DEFAULT_TTL_MILLIS))
                .signWith(KEY, SignatureAlgorithm.HS256)
                .compact();
    }
    /**
     * 解析并验证 JWT，成功返回 Claims，失败返回 null（过期/签名错误等）
     * 使用示例：Claims c = parseToken(token); if (c != null) { ... }
     */
    public static Claims parseToken(String token) {
        if (token == null || token.isBlank()) return null;
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            System.out.println("Token 已过期: " + e.getMessage());
        } catch (JwtException | IllegalArgumentException e) {
            System.out.println("无效的 token: " + e.getMessage());
        }
        return null;
    }
}
