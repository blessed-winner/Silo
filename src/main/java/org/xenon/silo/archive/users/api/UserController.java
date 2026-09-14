package org.xenon.silo.archive.users.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xenon.silo.archive.users.application.GetUserProfileUseCase;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final GetUserProfileUseCase getUserProfileUseCase;

    @GetMapping("/me")
    public ResponseEntity<UserProfileResponse> getUserProfile(){
        return ResponseEntity.ok(getUserProfileUseCase.execute());
    }
}
