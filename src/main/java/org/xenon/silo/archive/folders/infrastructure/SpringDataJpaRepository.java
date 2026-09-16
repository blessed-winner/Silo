package org.xenon.silo.archive.folders.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.xenon.silo.archive.users.infrastructure.persistence.UserJpaEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpringDataJpaRepository extends JpaRepository<FolderJpaEntity, UUID> {
    Optional<FolderJpaEntity> findbyIdAndOwnerId(UUID id, UUID ownerId);

    Optional<FolderJpaEntity> findByNameAndOwnerId(String name,UUID ownerId);

    List<FolderJpaEntity> findAllByOwnerIdAndIsActiveTrue(UUID ownerId);

    List<FolderJpaEntity> findAllByOwnerIdAndIsActiveTrueAndParentId(UUID ownerId, UUID parentId);

    UUID owner(UserJpaEntity owner);
}
