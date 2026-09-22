package org.xenon.silo.archive.documents.infrastructure;

import org.mapstruct.Mapper;
import org.xenon.silo.archive.documents.domain.Document;

@Mapper(componentModel = "spring")
public interface DocumentMapper {
    Document toDomain(DocumentJpaEntity entity);

    DocumentJpaEntity toEntity(Document document);
}
