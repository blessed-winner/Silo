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
    private boolean isActive;

    public Folder(UUID id, String name, User owner, Folder parent, boolean isActive){
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.parent = parent;
        this.isActive = isActive;
    }

    public static Folder create(String name, Folder parent, User owner){
        return new Folder(
                UUID.randomUUID(),
                name,
                owner,
                parent,
                true
        );
    }

    public void rename(String newName){
        if(newName.equals(this.name)){
            return;
        }

        if(newName.isBlank()){
            return;
        }
        this.name = newName;
    }

    public void changeParent(Folder newParent){
        if(newParent.id.equals(this.parent.id)){
            return;
        }

        if(newParent.id.equals(this.id)){
            return;
        }
        this.parent = newParent;
    }

    public void deactivate(){
        if(!this.isActive){
            return;
        }
        this.isActive = false;
    }
}
