package org.xenon.silo.archive.folders.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.xenon.silo.archive.folders.application.CreateFolderUseCase;
import org.xenon.silo.archive.folders.application.GetFolderUseCase;
import org.xenon.silo.archive.folders.application.ListUserRootFoldersUseCase;
import org.xenon.silo.archive.folders.application.UpdateFolderUseCase;
import org.xenon.silo.archive.folders.domain.Folder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/folders")
@RequiredArgsConstructor
public class FolderController {
    private final CreateFolderUseCase createFolderUseCase;
    private final ListUserRootFoldersUseCase listUserFoldersUseCase;
    private final GetFolderUseCase getFolderUseCase;
    private final UpdateFolderUseCase updateFolderUseCase;

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

    @GetMapping("/{id}")
    public ResponseEntity<FolderResponse> getFolder(@RequestParam UUID id){
        return ResponseEntity.ok(getFolderUseCase.execute(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateFolder(
            @RequestParam UUID id,
            @RequestBody FolderUpdateCommand command
    ){
        updateFolderUseCase.execute(id, command);
        return ResponseEntity.noContent().build();
    }
}
