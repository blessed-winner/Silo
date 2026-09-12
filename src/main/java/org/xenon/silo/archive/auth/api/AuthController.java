package org.xenon.silo.archive.auth.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.xenon.silo.archive.auth.application.LoginUseCase;
import org.xenon.silo.archive.auth.application.RegisterUseCase;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final LoginUseCase loginUseCase;
    private final RegisterUseCase registerUseCase;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(
            @Valid @RequestBody RegisterCommand command,
            UriComponentsBuilder uriBuilder
    ){
        var userResponse = registerUseCase.execute(command);
        var uri = uriBuilder.path("/api/users/{id}").buildAndExpand(userResponse.id()).toUri();
        return ResponseEntity.created(uri).body(userResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginCommand command){
        var authResponse = loginUseCase.execute(command);
        return ResponseEntity.ok(authResponse);
    }
}
