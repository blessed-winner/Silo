package org.xenon.silo.archive.folders.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.xenon.silo.archive.folders.api.FolderResponse;
import org.xenon.silo.archive.folders.domain.Folder;
import org.xenon.silo.archive.folders.domain.FolderRepository;
import org.xenon.silo.archive.shared.lib.GetAuthenticatedUserId;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetChildrenUseCase {
    private final FolderRepository folderRepository;
    private final GetAuthenticatedUserId getAuthenticatedUserId;

    public List<FolderResponse> execute(UUID parentId){
        UUID currentUser = getAuthenticatedUserId.getAuthenticatedUser();
        return folderRepository.findAllByOwnerIdAndIsActiveTrueAndParentId(currentUser, parentId)
                .stream()
                .map(folder ->new FolderResponse(
                folder.getId(),
                folder.getName()
        )).toList();
    }
}
