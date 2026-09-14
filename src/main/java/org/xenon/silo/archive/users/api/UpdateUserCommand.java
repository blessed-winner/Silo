package org.xenon.silo.archive.users.api;



public record UpdateUserCommand(
        String email,
        String firstName,
        String lastName
){}
