package org.xenon.silo.archive.auth.infrastructure.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.xenon.silo.archive.shared.config.JwtConfig;
import org.xenon.silo.archive.users.domain.User;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JwtService {
   private final JwtConfig jwtConfig;

   public String generateAccessToken(User user){return buildToken(user,jwtConfig.getAccessTokenExpiration());}

   public String generateRefreshToken(User user){return buildToken(user,jwtConfig.getRefreshTokenExpiration());}

   private String buildToken(User user, long expirationDate){
        Date expiryMillis = new Date(new Date().getTime() + expirationDate * 1000);
        return Jwts.builder()
                .subject(user.getId().toString())
                .claim("email",user.getEmail())
                .issuedAt(new Date())
                .expiration(expiryMillis)
                .signWith(jwtConfig.getSecretKey())
                .compact();
   }

   public Claims extractClaims(String token){
       return Jwts.parser()
               .verifyWith(jwtConfig.getSecretKey())
               .build()
               .parseSignedClaims(token)
               .getPayload();
   }

   public boolean isTokenValid(String token){
       try{
           extractClaims(token);
           return true;
       }
       catch (Exception e){
           return false;
       }
   }

   public UUID extractUserId(String token){
       return UUID.fromString(extractClaims(token).getSubject());
   }

   public String extractUserEmail(String token){
       return extractClaims(token).get("email").toString();
   }
}
