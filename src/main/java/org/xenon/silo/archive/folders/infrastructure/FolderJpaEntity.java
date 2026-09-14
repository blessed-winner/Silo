package org.xenon.silo.archive.folders.infrastructure;

import jakarta.persistence.*;
import lombok.*;
import org.xenon.silo.archive.documents.infrastructure.DocumentJpaEntity;
import org.xenon.silo.archive.shared.persistence.BaseEntity;
import org.xenon.silo.archive.users.infrastructure.persistence.UserJpaEntity;

import java.util.List;

@Entity
@Table(name = "folders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class FolderJpaEntity extends BaseEntity {
    private String name;
    private boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private UserJpaEntity owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private FolderJpaEntity parent;

    @OneToMany(mappedBy = "parent")
    private List<FolderJpaEntity> children;

    @OneToMany(mappedBy = "folder")
    private List<DocumentJpaEntity> documents;

    public FolderJpaEntity(String name, UserJpaEntity owner, FolderJpaEntity parent){
        this.name = name;
        this.owner = owner;
        this.parent = parent;
    }
}
