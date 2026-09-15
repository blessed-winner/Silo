package org.xenon.silo.archive.folders.api;

import java.util.UUID;

public record FolderCreateCommand(
        String parentName,
        String name
){}
