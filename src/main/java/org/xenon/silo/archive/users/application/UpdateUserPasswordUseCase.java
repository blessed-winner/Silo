package org.xenon.silo.archive.users.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.xenon.silo.archive.auth.application.PasswordHasher;
import org.xenon.silo.archive.shared.lib.GetAuthenticatedUserId;
import org.xenon.silo.archive.users.api.UpdatePasswordCommand;
import org.xenon.silo.archive.users.domain.User;
import org.xenon.silo.archive.users.domain.UserRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateUserPasswordUseCase {
    private final PasswordHasher passwordHasher;
    private final UserRepository userRepository;
    private final GetAuthenticatedUserId getAuthenticatedUserId;

    public void execute(UpdatePasswordCommand command){
        UUID currentUser = getAuthenticatedUserId.getAuthenticatedUser();
        User user = userRepository.findById(currentUser).orElseThrow(()->new RuntimeException("User not found"));

        if(passwordHasher.matches(command.oldPassword(),user.getPasswordHash())){
            user.changePassword(command.newPassword());
        }
    }
}
