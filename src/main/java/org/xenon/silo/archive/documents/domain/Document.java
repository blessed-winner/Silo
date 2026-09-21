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
    private String displayName;
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
            String displayName,
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
        this.displayName = displayName;
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
            String displayName,
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
                displayName,
                storageKey,
                mimeType,
                fileSize,
                folder,
                owner,
                description,
                null
        );
    }

    public void rename(String newName){
         if(newName == null || newName.isBlank()){
             throw new IllegalArgumentException("The new name cannot be null or blank");
         }

         if(this.displayName.equals(newName)){
             throw new IllegalArgumentException("The new name already exists");
         }

         this.displayName = newName;
    }
}
