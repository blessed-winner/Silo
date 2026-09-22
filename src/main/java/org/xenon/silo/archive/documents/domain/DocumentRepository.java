package org.xenon.silo.archive.documents.domain;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DocumentRepository {
    Optional<Document> findByIdAndOwnerId(UUID id, UUID ownerId);

    Optional<Document> findByDisplayNameAndOwnerId(String displayName, UUID ownerId);

    Document save(Document document);

    List<Document> findAllByOwnerId(UUID ownerId);
}
