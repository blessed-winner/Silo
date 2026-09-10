package org.xenon.silo.archive.documents.infrastructure;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.xenon.silo.archive.folders.infrastructure.FolderJpaEntity;
import org.xenon.silo.archive.shared.persistence.BaseEntity;
import org.xenon.silo.archive.users.infrastructure.persistence.UserJpaEntity;

import java.time.Instant;

@Entity
@Table(name = "documents")
@NoArgsConstructor
@Getter
public class DocumentJpaEntity extends BaseEntity {
    private String name;

    @Column(name = "original_file_name")
    private String originalFileName;

    @Column(name = "storage_key")
    private String storageKey;

    @Column(name = "mime_type")
    private String mimeType;

    @Column(name = "file_size")
    private String fileSize;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private UserJpaEntity owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "folder_id")
    private FolderJpaEntity folder;

    private String description;

    @Column(name = "deleted_at")
    private Instant deletedAt;

    public DocumentJpaEntity(String name, String originalFileName, String storageKey, String mimeType, String fileSize, String description){
        this.name = name;
        this.originalFileName = originalFileName;
        this.storageKey = storageKey;
        this.mimeType = mimeType;
        this.fileSize = fileSize;
        this.description = description;
    }
}
