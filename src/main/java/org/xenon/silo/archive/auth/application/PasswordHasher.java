package org.xenon.silo.archive.auth.application;

import org.springframework.stereotype.Component;

@Component
public interface PasswordHasher {
    String hash(String password);

    boolean matches(String password, String hash);
}
