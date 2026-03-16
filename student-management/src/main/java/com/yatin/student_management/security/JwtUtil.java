package com.yatin.student_management.security;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private final Key key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);

    // public String generateToken(String username){

    //     return Jwts.builder()
    //             .setSubject(username)
    //             .setIssuedAt(new Date())
    //             .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
    //             .signWith(key)
    //             .compact(); 
    // }

  public String generateToken(String username, String role) {

    return Jwts.builder()
            .setSubject(username)
            .claim("role", role)   // ⭐ ADD THIS
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
            .signWith(key)
            .compact(); 
}

    public String extractUsername(String token){

    return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
}



public String extractRole(String token) {
    return extractAllClaims(token).get("role", String.class);
}

private Claims extractAllClaims(String token){
    return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody();
}

}