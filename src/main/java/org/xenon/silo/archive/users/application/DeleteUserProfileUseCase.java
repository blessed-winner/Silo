package org.xenon.silo.archive.users.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.xenon.silo.archive.shared.lib.GetAuthenticatedUserId;
import org.xenon.silo.archive.users.domain.User;
import org.xenon.silo.archive.users.domain.UserRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteUserProfileUseCase {
    private final UserRepository userRepository;
    private final GetAuthenticatedUserId getAuthenticatedUserId;

    public void execute(){
        UUID currentUser = getAuthenticatedUserId.getAuthenticatedUser();
        User user = userRepository.findById(currentUser).orElseThrow(()->new RuntimeException("User not found"));

        user.disable();
        userRepository.save(user);
    }
}
