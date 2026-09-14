package org.xenon.silo.archive.folders.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.xenon.silo.archive.folders.domain.FolderRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpringDataJpaRepository extends JpaRepository<FolderJpaEntity, UUID> {

    List<FolderJpaEntity> findAllByOwnerIdAndIsActiveTrue(UUID ownerId);

    List<FolderRepository> findAllByOwnerIdAndIsActiveTrueAndParentId(UUID ownerId, UUID parentId);
}
