package org.xenon.silo.archive.auth.infrastructure.security;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.xenon.silo.archive.shared.config.JwtConfig;

@Service
@RequiredArgsConstructor
public class JwtService {
   private final JwtConfig jwtConfig;

   private String buildToken(){

   }
}
