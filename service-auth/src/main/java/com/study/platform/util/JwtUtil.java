package com.study.platform.util;

import com.study.interaction.dto.auth.RefreshToken;
import com.study.interaction.dto.auth.TokenPair;
import com.study.platform.entity.LoginInformation;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.HmacUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;

@Component
@Slf4j
public class JwtUtil {

    protected final int  ACCESS_TOKEN_EXPIRATION_MS  = 900000; // 30 минут
    protected final long REFRESH_TOKEN_EXPIRATION_MS = 7 * 24 * 60 * 60; // 7 дней

    @Value("${jwt.secret}")
    private String jwtSecret;

    public TokenPair generateTokenPair(LoginInformation user) {
        return new TokenPair(
                generateAccessToken(user),
                generateRefreshToken().getToken()
        );
    }

    public String generateAccessToken(LoginInformation user) {
        return Jwts.builder()
                .setSubject(user.getId().toString())
                .claim("email", user.getEmail())
                .claim("role", user.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION_MS))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public RefreshToken generateRefreshToken() {
        byte[] randomBytes = new byte[32];
        new SecureRandom().nextBytes(randomBytes);
        String token = Base64.getEncoder().encodeToString(randomBytes);

        String hash = generateHashByToken(token);

        return new RefreshToken(token, hash,
                new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION_MS));
    }

    public boolean validateAccessToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            log.error("Invalid JWT: {}", e.getMessage());
            return false;
        }
    }

    public String generateHashByToken(String token) {
        return HmacUtils.hmacSha256Hex(jwtSecret, token);
    }

    public String getUserIdFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

}