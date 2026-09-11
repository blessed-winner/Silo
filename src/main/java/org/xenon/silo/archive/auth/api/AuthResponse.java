package org.xenon.silo.archive.auth.api;

public record AuthResponse(
        String accessToken,
        String refreshToken
){}
