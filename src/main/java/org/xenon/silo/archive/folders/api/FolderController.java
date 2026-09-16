package org.xenon.silo.archive.folders.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.xenon.silo.archive.folders.application.CreateFolderUseCase;
import org.xenon.silo.archive.folders.application.ListUserFoldersUseCase;
import org.xenon.silo.archive.folders.domain.Folder;

import java.util.List;

@RestController
@RequestMapping("/api/folders")
@RequiredArgsConstructor
public class FolderController {
    private final CreateFolderUseCase createFolderUseCase;
    private final ListUserFoldersUseCase listUserFoldersUseCase;

    @PostMapping
    public ResponseEntity<Folder> createFolder(
            @RequestBody FolderCreateCommand command,
            UriComponentsBuilder uriBuilder
            ){
        Folder result = createFolderUseCase.execute(command);
        var uri = uriBuilder.path("/folders/{id}").buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(uri).body(result);
    }

    @GetMapping
    public ResponseEntity<List<FolderResponse>> listFolders(){
        return ResponseEntity.ok(listUserFoldersUseCase.execute());
    }
}
