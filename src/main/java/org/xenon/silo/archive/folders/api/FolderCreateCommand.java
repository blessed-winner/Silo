package org.xenon.silo.archive.folders.api;

import java.util.UUID;

public record FolderCreateCommand(
        UUID parentId,
        String name
){}
