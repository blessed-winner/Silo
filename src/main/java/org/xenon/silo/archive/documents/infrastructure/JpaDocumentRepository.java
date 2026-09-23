package org.xenon.silo.archive.documents.infrastructure;


import org.springframework.stereotype.Repository;
import org.xenon.silo.archive.documents.domain.Document;
import org.xenon.silo.archive.documents.domain.DocumentRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaDocumentRepository implements DocumentRepository {

    private final DocumentMapper documentMapper;
    private final SpringDataJpaRepository springDataJpaRepository;

    public JpaDocumentRepository(DocumentMapper documentMapper, SpringDataJpaRepository springDataJpaRepository){
        this.documentMapper = documentMapper;
        this.springDataJpaRepository = springDataJpaRepository;
    }

    @Override
    public Optional<Document> findByIdAndOwnerId(UUID id, UUID ownerId){
        return springDataJpaRepository.findByIdAndOwnerId(id, ownerId).map(documentMapper::toDomain);
    }

    @Override
    public Optional<Document> findByDisplayNameAndOwnerId(String name, UUID ownerId){
        return springDataJpaRepository.findByDisplayNameAndOwnerId(name, ownerId).map(documentMapper::toDomain);
    }

    @Override
    public Document save(Document document){
        DocumentJpaEntity entity = documentMapper.toEntity(document);
        springDataJpaRepository.save(entity);
        return documentMapper.toDomain(entity);
    }

    public List<Document> findAllByOwnerId(UUID ownerId){
        return springDataJpaRepository.findAllByOwnerId(ownerId)
                                      .stream()
                                      .map(documentMapper::toDomain)
                                      .toList();
    }

}
