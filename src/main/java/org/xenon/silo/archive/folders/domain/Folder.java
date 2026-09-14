package org.xenon.silo.archive.folders.domain;

import lombok.Getter;
import org.xenon.silo.archive.documents.domain.Document;
import org.xenon.silo.archive.users.domain.User;

import java.util.List;
import java.util.UUID;

@Getter
public class Folder {
    private UUID id;
    private String name;
    private User owner;
    private Folder parent;
    private List<Folder> children;
    private List<Document> documents;

    public Folder(UUID id, String name, User owner, Folder parent){
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.parent = parent;
    }
}
