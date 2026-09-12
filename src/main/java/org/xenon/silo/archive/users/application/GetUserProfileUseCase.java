package org.xenon.silo.archive.users.application;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.xenon.silo.archive.users.api.UserProfileResponse;
import org.xenon.silo.archive.users.domain.UserRepository;

@Service
@RequiredArgsConstructor
public class GetUserProfileUseCase {
    private final UserRepository userRepository;
    public UserProfileResponse execute(){
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var userId = authentication.getPrincipal();
    }
}
