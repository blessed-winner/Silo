package org.xenon.silo.archive.documents.domain;

import lombok.Getter;
import org.xenon.silo.archive.folders.domain.Folder;
import org.xenon.silo.archive.users.domain.User;

import java.time.Instant;
import java.util.UUID;

@Getter
public class Document {
    private UUID id;
    private String originalFileName;
    private String storageKey;
    private String mimeType;
    private String fileSize;
    private Folder folder;
    private User owner;
    private String description;
    private Instant deletionDate;

    public Document(
            UUID id,
            String originalFileName,
            String storageKey,
            String mimeType,
            String fileSize,
            Folder folder,
            User owner,
            String description,
            Instant deletionDate
    ){
        this.id = id;
        this.originalFileName = originalFileName;
        this.storageKey = storageKey;
        this.mimeType = mimeType;
        this.fileSize = fileSize;
        this.folder = folder;
        this.owner = owner;
        this.description = description;
        this.deletionDate = deletionDate;
    }

    public static Document create(
            String originalFileName,
            String storageKey,
            String mimeType,
            String fileSize,
            Folder folder,
            User owner,
            String description
    ){
        return new Document(
                UUID.randomUUID(),
                originalFileName,
                storageKey,
                mimeType,
                fileSize,
                folder,
                owner,
                description,
                null
        );
    }
}
