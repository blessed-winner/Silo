package org.xenon.silo.archive.documents.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.xenon.silo.archive.documents.domain.Document;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpringDataJpaRepository extends JpaRepository<DocumentJpaEntity, UUID> {
    Optional<DocumentJpaEntity> findByIdAndOwnerId(UUID id, UUID ownerId);

    Optional<DocumentJpaEntity> findByDisplayNameAndOwnerId(String displayName, UUID ownerId);

    List<DocumentJpaEntity> findAllByOwnerId(UUID ownerId);
}
