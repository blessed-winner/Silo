package org.xenon.silo.archive.folders.api;

import jakarta.validation.constraints.NotBlank;

public record FolderCreateCommand(
        String parentName,

        @NotBlank
        String name
){}
