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

    public static User create(String email, String firstName, String lastName, String passwordHash){
      return new User(
              UUID.randomUUID(),
              email,
              firstName,
              lastName,
              passwordHash,
              true
      );
    }

    public void disable(){
        if(!isEnabled){
            return;
        }
        isEnabled = false;
    }

    public void changeFirstName(String firstName){
        this.firstName = firstName;
    }

    public void changeLastName(String lastName){
        this.lastName = lastName;
    }

    public void changeEmail(String email){
        this.email = email;
    }

}
