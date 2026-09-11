package org.xenon.silo.archive.auth.domain;

import io.jsonwebtoken.Claims;
import org.xenon.silo.archive.users.domain.User;

import java.util.UUID;

public interface JwtService {
    public String generateAccessToken(User user);
    public String generateRefreshToken(User user);
    public Claims extractClaims(String token);
    public boolean isTokenValid(String token);
    public UUID extractUserId(String token);
    public String extractUserEmail(String token);
}
