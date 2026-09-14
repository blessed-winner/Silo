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
        if(firstName == null || firstName.isBlank()){
            throw new IllegalArgumentException("First name cannot be null or blank");
        }
        this.firstName = firstName;
    }

    public void changeLastName(String lastName){
        if(lastName == null || lastName.isBlank()){
            throw new IllegalArgumentException("Last name cannot be null or blank");
        }
        this.lastName = lastName;
    }

    public void changeEmail(String email){
        if(email == null || email.isBlank()){
            throw new IllegalArgumentException("Email cannot be null or blank");
        }
        this.email = email;
    }

}
