package org.xenon.silo.archive.auth.infrastructure.util;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.xenon.silo.archive.auth.application.PasswordHasher;

@Service
@RequiredArgsConstructor
public class PasswordHasherImpl implements PasswordHasher {
    private final PasswordEncoder passwordEncoder;

    @Override
    public String hash(String password){
        return passwordEncoder.encode(password);
    }

    @Override
    public boolean matches(String password, String hash){
        return passwordEncoder.matches(password, hash);
    }
}
