package org.xenon.silo.archive.folders.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SpringDataJpaRepository extends JpaRepository<FolderJpaEntity, UUID> {

    List<FolderJpaEntity> findAllByOwnerIdAndIsActiveTrue(UUID ownerId);

    List<FolderJpaEntity> findAllByOwnerIdAndIsActiveTrueAndParentId(UUID ownerId, UUID parentId);
}
