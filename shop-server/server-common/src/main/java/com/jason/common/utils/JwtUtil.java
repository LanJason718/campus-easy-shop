package com.jason.common.utils;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map; 

public class JwtUtil {

    private static final String SECRET = "LanJason";
    private static final String TOKEN_PREFIX = "Bearer ";
    private static final long EXPIRES_TIME = 60 * 60 * 24 * 7 * 1000; // 7天有效期，单位为毫秒

    /**
     * 生成JWT令牌
     *
     * @param claims JWT中存储的声明
     * @return 生成的JWT令牌字符串
     */
    public static String generateToken(Map<String, Object> claims) {
        return TOKEN_PREFIX + Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRES_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    /**
     * 验证并解析JWT令牌
     *
     * @param token JWT令牌
     * @return 令牌中的声明内容
     */
    public static Map<String, Object> resolveToken(String token) {
        if (token == null || !token.startsWith(TOKEN_PREFIX)) {
            throw new RuntimeException("令牌格式错误或为空");
        }
        try {
            return Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.substring(TOKEN_PREFIX.length()))
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("令牌已过期", e);
        } catch (Exception e) {
            throw new RuntimeException("令牌解析异常", e);
        }
    }

    /**
     * 从令牌中获取userID
     *
     * @param token JWT令牌
     * @return userId
     */
    public static String getUserId(String token) {
        Map<String, Object> claims = resolveToken(token);
        Object userId = claims.get("userId");
        if (userId instanceof String) {
            return (String) userId;
        } else {
            throw new RuntimeException("userId 无效或不存在");
        }
    }
}
