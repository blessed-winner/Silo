package org.xenon.silo.archive.folders.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.xenon.silo.archive.folders.api.FolderResponse;
import org.xenon.silo.archive.folders.domain.FolderRepository;
import org.xenon.silo.archive.shared.lib.GetAuthenticatedUserId;
import org.xenon.silo.archive.users.domain.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ListUserFoldersUseCase {
    private final FolderRepository folderRepository;
    private final UserRepository userRepository;
    private final GetAuthenticatedUserId getAuthenticatedUserId;

    public List<FolderResponse> execute(){
        UUID currentUser = getAuthenticatedUserId.getAuthenticatedUser();
        return folderRepository.findAllByOwnerIdAndIsActiveTrue(currentUser).stream().map();
    }
}
