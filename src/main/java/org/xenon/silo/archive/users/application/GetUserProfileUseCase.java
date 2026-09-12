package org.xenon.silo.archive.users.application;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.xenon.silo.archive.shared.lib.GetAuthenticatedUserId;
import org.xenon.silo.archive.users.api.UserProfileResponse;
import org.xenon.silo.archive.users.domain.UserRepository;

@Service
@RequiredArgsConstructor
public class GetUserProfileUseCase {
    private final UserRepository userRepository;
    private final GetAuthenticatedUserId getAuthenticatedUserId;
    public UserProfileResponse execute(){
        var currentUser = getAuthenticatedUserId.getAuthenticatedUser();
        var user = userRepository.findById(currentUser).orElseThrow(()->new IllegalArgumentException("User not found"));
        return new UserProfileResponse(
                user.getEmail(),
                user.getFirstName(),
                user.getLastName()
        );
    }
}
