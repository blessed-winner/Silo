package org.xenon.silo.archive.auth.application;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.xenon.silo.archive.auth.api.AuthResponse;
import org.xenon.silo.archive.auth.api.LoginCommand;
import org.xenon.silo.archive.auth.domain.JwtService;
import org.xenon.silo.archive.users.domain.UserRepository;

@Service
@RequiredArgsConstructor
public class LoginUseCase {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse execute(LoginCommand command){
       var existingUser = userRepository.findByEmail(command.email()).orElseThrow(()->new IllegalArgumentException("User not found"));
            authenticationManager.authenticate(
                 new UsernamePasswordAuthenticationToken(
                         command.email(),
                         command.password()
                 )
            );

            var accessToken = jwtService.generateAccessToken(existingUser);
            var refreshToken = jwtService.generateRefreshToken(existingUser);

            return new AuthResponse(accessToken, refreshToken);
    }
}
