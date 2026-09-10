package org.xenon.silo.archive.users.domain;

import lombok.Getter;

import java.util.UUID;

@Getter
public class User {
    private final UUID id;
    private String email;
    private String firstName;
    private String lastName;
    private String passwordHash;
    private boolean isEnabled;

    public User(UUID id, String email, String firstName, String lastName, String passwordHash, boolean isEnabled) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.passwordHash = passwordHash;
        this.isEnabled = isEnabled;
    }
}
