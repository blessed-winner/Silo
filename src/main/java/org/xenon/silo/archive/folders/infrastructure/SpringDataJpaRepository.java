package org.xenon.silo.archive.folders.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpringDataJpaRepository extends JpaRepository<FolderJpaEntity, UUID> {
    Optional<FolderJpaEntity> findByName(String name);

    List<FolderJpaEntity> findAllByOwnerIdAndIsActiveTrue(UUID ownerId);

    List<FolderJpaEntity> findAllByOwnerIdAndIsActiveTrueAndParentId(UUID ownerId, UUID parentId);
}
