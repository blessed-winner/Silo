package org.xenon.silo.archive.documents.api;

import org.xenon.silo.archive.folders.api.FolderResponse;

import java.util.UUID;

public record DocumentResponse(
        UUID id,
        String originalName,
        String displayName,
        String mimeType,
        String description,
        FolderResponse folder
){}
