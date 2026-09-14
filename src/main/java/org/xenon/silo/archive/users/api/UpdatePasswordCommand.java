package org.xenon.silo.archive.users.api;

import jakarta.validation.constraints.NotBlank;

public record UpdatePasswordCommand(
        @NotBlank
        String oldPassword,

        @NotBlank
        String newPassword
){}