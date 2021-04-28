package com.mall.demo.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JwtToken生成工具类
 */
@Component
public class JwtTokenUtil {
    public static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);
    public static final String CLAIM_KEY_USERNAME = "sub";
    public static final String CLAIM_KEY_CREATED = "created";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * 生成token
     * @param claims 负载信息
     * @return 加密后的token信息
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 生成过期时间
     * @return 过期时间
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * 从token中解密负载信息
     * @param token token密文
     * @return 负载键值对信息
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            LOGGER.error("JWT格式验证失败: {}", token);
        }
        return claims;
    }

    /**
     * 从token获取username
     * @param token 加密信息
     * @return username
     */
    public String getUserNameFromToken(String token) {
        String username = null;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            LOGGER.error("Token 解密失败");
        }
        return username;
    }

    /**
     * 验证token是否有效
     * @param token 加密token
     * @param userDetails 用户信息
     * @return 是否有效
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUserNameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * 判断token是否失效
     * @param token 加密token
     * @return 是否失效过期
     */
    private boolean isTokenExpired(String token) {
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }

    /**
     * 从token中获取过期时间
     * @param token 加密token
     * @return 过期时间
     */
    private Date getExpiredDateFromToken(String token) {
        Date expiredDate = null;
        try {
            Claims claims = getClaimsFromToken(token);
            expiredDate = claims.getExpiration();
        } catch (Exception e) {
            LOGGER.error("Token 解密失败");
        }
        return expiredDate;
    }

    /**
     * 根据用户信息生成token
     * @param userDetails 用户信息
     * @return 返回token
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * 判断token是否可以刷新
     * @param token 加密token
     * @return 是否可以刷新
     */
    public boolean canRefresh(String token) {
        return !isTokenExpired(token);
    }

    /**
     * 刷新token
     * @param token 加密token
     * @return 刷新后的token
     */
    public String refreshToken(String token) {
        Claims claims = getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

}
