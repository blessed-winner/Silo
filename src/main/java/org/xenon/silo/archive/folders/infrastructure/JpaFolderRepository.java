package org.xenon.silo.archive.folders.infrastructure;


import org.springframework.stereotype.Repository;
import org.xenon.silo.archive.folders.domain.Folder;
import org.xenon.silo.archive.folders.domain.FolderRepository;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaFolderRepository implements FolderRepository {

    private final FolderMapper folderMapper;
    private final SpringDataJpaRepository springDataJpaRepository;

    public JpaFolderRepository(FolderMapper folderMapper, SpringDataJpaRepository springDataJpaRepository){
        this.folderMapper = folderMapper;
        this.springDataJpaRepository = springDataJpaRepository
    }

    @Override
    public Optional<Folder> findById(UUID id){
       return springDataJpaRepository.findById(id).map(folderMapper::toDomain);
    }

    @Override
    public Folder save(Folder folder){
        FolderJpaEntity toSave = folderMapper.toEntity(folder);
        springDataJpaRepository.save(toSave);
        return folderMapper.toDomain(toSave);
    }

    @Override
    public List<Folder> findAllByOwnerIdAndIsActiveTrue(UUID ownerId){
        return springDataJpaRepository.findAllByOwnerIdAndIsActiveTrue(ownerId)
                .stream()
                .map(folderMapper::toDomain)
                .toList();
    }

    @Override
    public List<Folder> findAllByOwnerIdAndIsActiveTrueAndParentId(UUID ownerId, UUID parentId){
        return springDataJpaRepository.findAllByOwnerIdAndIsActiveTrueAndParentId(ownerId, parentId)
                .stream()
                .map(folderMapper::toDomain)
                .toList();
    }

}
