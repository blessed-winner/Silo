package org.xenon.silo.archive.users.api;

public record UserProfileResponse(
        String email,
        String firstName,
        String lastName
){}
