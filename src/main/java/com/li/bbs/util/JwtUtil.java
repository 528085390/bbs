package com.li.bbs.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component // 让Spring管理这个类
public class JwtUtil {

    // 1. 定义一个密钥 (用于签名JWT，非常重要，后续可以移到配置文件)
    private final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    // 2. 定义Token的过期时间 (例如：1小时，单位是毫秒)
    private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

    // --- 生成Token的方法 ---
    public String generateToken(String username, Integer userId) {
        Map<String, Object> claims = new HashMap<>();
        // 你可以在claims中放入任何你想包含在token中的非敏感信息
        claims.put("username", username);
        claims.put("userId", userId);


        return Jwts.builder()
                .setClaims(claims) // 设置自定义声明
                .setSubject(username) // 设置主题，通常是用户名
                .setIssuedAt(new Date(System.currentTimeMillis())) // 设置签发时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // 设置过期时间
                .signWith(SECRET_KEY) // 使用新API直接传入密钥
                .compact(); // 生成JWT字符串
    }

    // --- 从Token中提取信息的方法 ---

    // 从Token中提取用户名
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // 从Token中提取id
    public Integer extractUserId(String token) {
        final Claims claims = extractAllClaims(token);
        return Integer.valueOf((String) claims.get("userId"));
    }

    // 从Token中提取过期时间
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // 提取单个声明的通用方法
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // 提取Token中的所有声明
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // --- 验证Token的方法 ---

    // 检查Token是否过期
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // 验证Token是否有效 (用户名匹配且未过期)
    public Boolean validateToken(String token, Integer userIdFromUserDetails) {
        final Integer userId = extractUserId(token);
        return (userId.equals(userIdFromUserDetails) && !isTokenExpired(token));
    }
}
