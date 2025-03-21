package com.lab.Assignment03.JwtAuthentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
//@ConfigurationProperties(prefix = "jwt.secret")
public class JwtUtil {
    @Value("${jwt.secret.key}")
    private String key; // Khóa bí mật để ký JWT
    // Thời gian hết hạn của token (1 giờ)
    private static final long EXPIRATION_TIME = 3600000;  // 1 hour in milliseconds

    // Tạo token
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Hết hạn sau 1 giờ
                .signWith(Keys.hmacShaKeyFor(key.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    // Extract username từ token
    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    // Lấy Claims từ token
    private Claims extractClaims(String token) {
        return Jwts.parserBuilder()  // Sử dụng parserBuilder() thay vì parser()
                .setSigningKey(Keys.hmacShaKeyFor(key.getBytes()))  // Đặt khóa bí mật
                .build()  // Xây dựng JwtParser
                .parseClaimsJws(token)  // Giải mã JWT và lấy Claims
                .getBody();  // Trả về phần body của JWT (là Claims)
    }

    // Kiểm tra token có hợp lệ không
    public boolean validateToken(String token, UserDetails userDetails) {
        return (userDetails.getUsername().equals(extractUsername(token)) && !isTokenExpired(token));
    }

    // Kiểm tra token hết hạn chưa
    private boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }
    // Phương thức này trích xuất role từ token
    public String extractRole(String token) {
        Claims claims = extractClaims(token);
        return claims.get("role", String.class); // Lấy role từ claim
    }
}
