
package com.BankingApplication.utils;


import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Component

public class JwtUtil {

    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
// new for role
    // public String extractRole(String token) {
    //     return Jwts.parser()
    //             .setSigningKey(getSignKey())
    //             .parseClaimsJws(token)
    //             .getBody()
    //             .get("role", String.class);  // Extract the role as a String
    // }
    public String extractRole(String token) {
        return (String) extractAllClaims(token).get("role");
    }
    

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // public Boolean validateToken(String token, UserDetails userDetails) {
    //     final String username = extractUsername(token);
    //     return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    // }

    public boolean validateToken(String token, String username) {
        return (username.equals(extractUsername(token)) && !isTokenExpired(token));
    }

    

    
    public String generateToken(String userName,String role){
        Map<String,Object> claims=new HashMap<>();
        return createToken(claims,userName,role);
    }

    private String createToken(Map<String, Object> claims, String userName,String role) {
        return Jwts.builder()
                // .setClaims(claims)
                .claim("role", role)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignKey() {
        byte[] keyBytes= Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);  
    }

}
