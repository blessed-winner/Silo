package org.xenon.silo.archive.auth.api;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "This field is required")
        @Email(message = "Email should be valid")
        String email,

        @NotBlank(message = "This field is required")
        String password
){}
