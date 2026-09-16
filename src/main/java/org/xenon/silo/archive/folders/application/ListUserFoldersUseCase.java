package org.xenon.silo.archive.folders.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.xenon.silo.archive.folders.api.FolderResponse;
import org.xenon.silo.archive.folders.domain.Folder;
import org.xenon.silo.archive.folders.domain.FolderRepository;
import org.xenon.silo.archive.shared.lib.GetAuthenticatedUserId;
import org.xenon.silo.archive.users.domain.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ListUserFoldersUseCase {
    private final FolderRepository folderRepository;
    private final GetAuthenticatedUserId getAuthenticatedUserId;

    public List<FolderResponse> execute(){
        UUID currentUser = getAuthenticatedUserId.getAuthenticatedUser();
        List<Folder> folders= folderRepository.findAllByOwnerIdAndIsActiveTrue(currentUser);
        List<FolderResponse> responses = new ArrayList<>();
        for(Folder folder:folders){
            responses.add(new FolderResponse(folder.getId(),folder.getName(),folder.getParent().getName()));
        }

        return responses;
    }
}
