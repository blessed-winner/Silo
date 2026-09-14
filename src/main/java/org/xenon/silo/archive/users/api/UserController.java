package org.xenon.silo.archive.users.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xenon.silo.archive.users.application.GetUserProfileUseCase;
import org.xenon.silo.archive.users.application.UpdateUserPasswordUseCase;
import org.xenon.silo.archive.users.application.UpdateUserUseCase;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final GetUserProfileUseCase getUserProfileUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final UpdateUserPasswordUseCase updateUserPasswordUseCase;

    @GetMapping("/me")
    public ResponseEntity<UserProfileResponse> getUserProfile(){
        return ResponseEntity.ok(getUserProfileUseCase.execute());
    }

    @PutMapping("/me")
    public ResponseEntity<Void> updateUserProfile(@RequestBody UpdateUserCommand command){
        updateUserUseCase.execute(command);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/me/password")
    public ResponseEntity<Void> updateUserPassword(@RequestBody UpdatePasswordCommand command){
        updateUserPasswordUseCase.execute(command);
        return ResponseEntity.noContent().build();
    }
}
