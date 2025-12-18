package com.example.demo.data.security;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import static java.security.KeyRep.Type.SECRET;

@Component
public class JwtUtility {
    private static final String token = "Mysecretkey1234567890";
    public String generateToken(String username) {
        String secretKey = "super-long-random-secret-key-at-least-256-bit";
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes());
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
    public Claims getToken(String token) {
        return Jwts.parser()
                .setSigningKey(String.valueOf(SECRET))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
