package org.xenon.silo.archive.folders.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.xenon.silo.archive.folders.domain.Folder;
import org.xenon.silo.archive.folders.domain.FolderRepository;
import org.xenon.silo.archive.shared.lib.GetAuthenticatedUserId;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteFolderUseCase {
    private final FolderRepository folderRepository;
    private final GetAuthenticatedUserId getAuthenticatedUserId;

    public void execute(UUID id){
        UUID currentUser = getAuthenticatedUserId.getAuthenticatedUser();
        Folder toDelete = folderRepository.findByIdAndOwnerId(id, currentUser).orElseThrow(()->new RuntimeException("Folder not found"));

        toDelete.deactivate();
        folderRepository.save(toDelete);
    }
}
