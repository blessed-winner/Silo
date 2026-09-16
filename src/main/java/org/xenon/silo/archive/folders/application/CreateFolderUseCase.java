package org.xenon.silo.archive.folders.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.xenon.silo.archive.folders.api.FolderCreateCommand;
import org.xenon.silo.archive.folders.domain.Folder;
import org.xenon.silo.archive.folders.domain.FolderRepository;
import org.xenon.silo.archive.shared.lib.GetAuthenticatedUserId;
import org.xenon.silo.archive.users.domain.User;
import org.xenon.silo.archive.users.domain.UserRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateFolderUseCase {
    private final FolderRepository folderRepository;
    private final GetAuthenticatedUserId getAuthenticatedUserId;
    private final UserRepository userRepository;

    public Folder execute(FolderCreateCommand command){
        UUID currentUser = getAuthenticatedUserId.getAuthenticatedUser();
        User authenticatedUser = userRepository.findById(currentUser).orElseThrow(()->new RuntimeException("User not found"));
        Optional<Folder> parent = folderRepository.findByNameAndOwnerId(command.parentName(),currentUser);

        var newFolder = Folder.create(
                command.name(),
                parent.orElse(null),
                authenticatedUser
        );

        return folderRepository.save(newFolder);
    }
}
