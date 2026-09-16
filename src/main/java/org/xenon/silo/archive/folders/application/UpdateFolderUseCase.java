package org.xenon.silo.archive.folders.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.xenon.silo.archive.folders.api.FolderResponse;
import org.xenon.silo.archive.folders.api.FolderUpdateCommand;
import org.xenon.silo.archive.folders.domain.Folder;
import org.xenon.silo.archive.folders.domain.FolderRepository;
import org.xenon.silo.archive.shared.lib.GetAuthenticatedUserId;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateFolderUseCase {
    private final FolderRepository folderRepository;
    private final GetAuthenticatedUserId getAuthenticatedUserId;

    public void execute(UUID id, FolderUpdateCommand command){

        UUID currentUser = getAuthenticatedUserId.getAuthenticatedUser();
        Folder folder = folderRepository.findByIdAndOwnerId(id, currentUser)
                                        .orElseThrow(()->new RuntimeException("Folder not found!"));

        if(command.name() != null && !command.name().isBlank()){
            folder.rename(command.name());
            folderRepository.save(folder);
        }

        Folder newParent = folderRepository.findByIdAndOwnerId(command.parentId(), currentUser)
                                           .orElseThrow(()->new RuntimeException("Folder not found!"));
        if(newParent == null){
            folder.changeParent(null);
            folderRepository.save(folder);
        } else {
            folder.changeParent(newParent);
            folderRepository.save(folder);
        }
    }
}
