package org.xenon.silo.archive.documents.api;

import java.io.InputStream;
import java.util.UUID;

public record DocumentCommand(
        UUID userId,
        String name,
        String description,
        UUID folderId,
        String originalFileName,
        String mimeType,
        Long fileSize,
        InputStream content
){}
