package org.xenon.silo.archive.folders.domain;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FolderRepository {
    Optional<Folder> findById(UUID id);

    Optional<Folder> findByNameAndOwnerId(String name, UUID ownerId);

    Folder save(Folder folder);

    List<Folder> findAllByOwnerIdAndIsActiveTrue(UUID ownerId);

    List<Folder> findAllByOwnerIdAndIsActiveTrueAndParentId(UUID ownerId, UUID parentId);
}
