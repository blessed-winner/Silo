package org.xenon.silo.archive.folders.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.xenon.silo.archive.folders.api.FolderCreateCommand;
import org.xenon.silo.archive.folders.domain.Folder;
import org.xenon.silo.archive.folders.domain.FolderRepository;
import org.xenon.silo.archive.shared.lib.GetAuthenticatedUserId;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateFolderUseCase {
    private final FolderRepository folderRepository;
    private final GetAuthenticatedUserId getAuthenticatedUserId;

    public Folder execute(FolderCreateCommand command){
        UUID currentUser = getAuthenticatedUserId.getAuthenticatedUser();
        Folder toSave = Folder.create(command.name(),command.);
    }
}
