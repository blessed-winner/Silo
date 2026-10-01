package org.xenon.silo.archive.documents.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.xenon.silo.archive.documents.api.DocumentCommand;
import org.xenon.silo.archive.documents.application.storage.DocumentStorageKeyGenerator;
import org.xenon.silo.archive.documents.domain.Document;
import org.xenon.silo.archive.documents.domain.DocumentRepository;
import org.xenon.silo.archive.documents.domain.DocumentStorage;
import org.xenon.silo.archive.folders.domain.Folder;
import org.xenon.silo.archive.folders.domain.FolderRepository;
import org.xenon.silo.archive.shared.lib.GetAuthenticatedUserId;
import org.xenon.silo.archive.users.domain.User;
import org.xenon.silo.archive.users.domain.UserRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateDocumentUseCase {
    private final DocumentRepository documentRepository;
    private final GetAuthenticatedUserId getAuthenticatedUserId;
    private final DocumentStorageKeyGenerator generator;
    private final FolderRepository folderRepository;
    private final UserRepository userRepository;
    private final DocumentStorage documentStorage;

    public Document execute(DocumentCommand command){
        UUID currentUser = getAuthenticatedUserId.getAuthenticatedUser();
        String storageKey = generator.generate(currentUser, command.originalFileName());
        Folder folder = folderRepository.findByIdAndOwnerId(command.folderId(), currentUser).orElseThrow();
        User authenticatedUser = userRepository.findById(currentUser).orElseThrow();

        documentStorage.store(storageKey, command.content());

        try{
            Document document = Document.create(
               command.originalFileName(),
               command.name(),
               storageKey,
               command.mimeType(),
               command.fileSize().toString(),
               folder,
               authenticatedUser,
               command.description()
            );

            return documentRepository.save(document);
        }catch (RuntimeException e){
           documentStorage.delete(storageKey);

           throw e;
        }
    }

}
