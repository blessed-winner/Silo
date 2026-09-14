package org.xenon.silo.archive.users.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.xenon.silo.archive.shared.lib.GetAuthenticatedUserId;
import org.xenon.silo.archive.users.api.UpdateUserCommand;
import org.xenon.silo.archive.users.domain.User;
import org.xenon.silo.archive.users.domain.UserRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateUserUseCase {
    private final UserRepository userRepository;
    private final GetAuthenticatedUserId getAuthenticatedUserId;

    public void execute(UpdateUserCommand command){
        UUID currentUser = getAuthenticatedUserId.getAuthenticatedUser();
        User user = userRepository.findById(currentUser).orElseThrow(()->new RuntimeException("User not found"));

        if(command.email() != null){
            user.changeEmail(command.email());
        }

        if(command.firstName() != null){
            user.changeFirstName(command.firstName());
        }

        if(command.lastName() != null){
            user.changeLastName(command.lastName());
        }

        userRepository.save(user);
    }
}
