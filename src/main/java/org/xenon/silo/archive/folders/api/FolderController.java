package org.xenon.silo.archive.folders.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.xenon.silo.archive.folders.application.CreateFolderUseCase;
import org.xenon.silo.archive.folders.domain.Folder;

@RestController
@RequestMapping("/api/folders")
@RequiredArgsConstructor
public class FolderController {
    private final CreateFolderUseCase createFolderUseCase;

    @PostMapping
    public ResponseEntity<Folder> createFolder(
            @RequestBody FolderCreateCommand command,
            UriComponentsBuilder uriBuilder
            ){
        Folder result = createFolderUseCase.execute(command);
        var uri = uriBuilder.path("/folders/{id}").buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(uri).body(result);
    }
}
