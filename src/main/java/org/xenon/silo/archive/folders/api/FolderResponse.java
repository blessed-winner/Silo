package org.xenon.silo.archive.folders.api;

import java.util.UUID;

public record FolderResponse(
        UUID id,
        String name
  ){}
