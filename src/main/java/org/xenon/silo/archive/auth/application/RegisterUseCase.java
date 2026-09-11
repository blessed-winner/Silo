package org.xenon.silo.archive.auth.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.xenon.silo.archive.auth.api.RegisterCommand;
import org.xenon.silo.archive.auth.api.UserResponse;
import org.xenon.silo.archive.users.domain.User;
import org.xenon.silo.archive.users.domain.UserRepository;

@Service
@RequiredArgsConstructor
public class RegisterUseCase {
    private final UserRepository userRepository;
    private final PasswordHasher passwordHasher;

    public UserResponse execute(RegisterCommand command){
       if(userRepository.existsByEmail(command.email())){
           throw new IllegalArgumentException("Email already exists");
       }

       var passwordHash = passwordHasher.hash(command.password());
       var newUser = User.create(
               command.email(),
               command.firstName(),
               command.lastName(),
               passwordHash
       );

      var saved = userRepository.save(newUser);

       return new UserResponse(
               saved.getId(),
               saved.getEmail(),
               saved.getFirstName(),
               saved.getLastName()
       );
    }
}
