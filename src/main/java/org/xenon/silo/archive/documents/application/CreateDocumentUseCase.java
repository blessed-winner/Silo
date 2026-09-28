package org.xenon.silo.archive.documents.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.xenon.silo.archive.documents.api.DocumentResponse;
import org.xenon.silo.archive.documents.domain.DocumentRepository;
import org.xenon.silo.archive.shared.lib.GetAuthenticatedUserId;

@Service
@RequiredArgsConstructor
public class CreateDocumentUseCase {
    private final DocumentRepository documentRepository;
    private final GetAuthenticatedUserId getAuthenticatedUserId;

    //File storage to be implemented
    public DocumentResponse execute(){

    }
}
