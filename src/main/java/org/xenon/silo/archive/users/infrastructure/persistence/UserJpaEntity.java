package org.xenon.silo.archive.users.infrastructure.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.xenon.silo.archive.documents.infrastructure.DocumentJpaEntity;
import org.xenon.silo.archive.folders.infrastructure.FolderJpaEntity;
import org.xenon.silo.archive.shared.persistence.BaseEntity;

import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserJpaEntity extends BaseEntity {
    private String email;

    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "is_enabled")
    private boolean isEnabled;

    @OneToMany(mappedBy = "owner")
    private List<FolderJpaEntity> folders;

    @OneToMany(mappedBy = "owner")
    private List<DocumentJpaEntity> document;

    public UserJpaEntity(String email,String passwordHash, String firstName, String lastName, boolean isEnabled){
        this.email = email;
        this.passwordHash = passwordHash;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isEnabled = isEnabled;
    }
}
