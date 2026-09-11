package org.xenon.silo.archive.auth.api;

import java.util.UUID;

public record UserResponse (
        UUID id,
        String email,
        String firstName,
        String lastName
){}
