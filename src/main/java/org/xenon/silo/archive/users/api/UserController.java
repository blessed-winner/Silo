package org.xenon.silo.archive.users.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xenon.silo.archive.users.application.GetUserProfileUseCase;
import org.xenon.silo.archive.users.application.UpdateUserUseCase;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final GetUserProfileUseCase getUserProfileUseCase;
    private final UpdateUserUseCase updateUserUseCase;

    @GetMapping("/me")
    public ResponseEntity<UserProfileResponse> getUserProfile(){
        return ResponseEntity.ok(getUserProfileUseCase.execute());
    }

    public ResponseEntity<Void> updateUserProfile(@RequestBody UpdateUserCommand command){
        updateUserUseCase.execute(command);
        return ResponseEntity.noContent().build();
    }
}
