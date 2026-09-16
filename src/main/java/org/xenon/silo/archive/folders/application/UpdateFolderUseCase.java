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

    public FolderResponse execute(UUID id, FolderUpdateCommand command){

        UUID currentUser = getAuthenticatedUserId.getAuthenticatedUser();
        Folder folder = folderRepository.findById(id).orElseThrow(()->new RuntimeException("Folder not found!"));

        if(!folder.getOwner().getId().equals(currentUser)){
            throw new RuntimeException("Folder does not belong to authenticated user!");
        }

        if(command.name() != null && !command.name().isBlank()){
            folder.rename(command.name());
        }

        if(!command.parentId().isBlank()){
            folderRepository.find
        }
    }
}
