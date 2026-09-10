package org.xenon.silo.archive.users.infrastructure;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.xenon.silo.archive.shared.persistence.BaseEntity;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class UserJpaEntity extends BaseEntity {

}
