package org.xenon.silo.archive.folders.infrastructure;

import org.mapstruct.Mapper;
import org.xenon.silo.archive.folders.domain.Folder;

@Mapper(componentModel = "spring")
public interface FolderMapper {
    Folder toDomain(FolderJpaEntity entity);

    FolderJpaEntity toEntity(Folder domain);
}
