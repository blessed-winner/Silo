package org.xenon.silo.archive.users.infrastructure.persistence;

import org.mapstruct.Mapper;
import org.xenon.silo.archive.users.domain.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toDomain(UserJpaEntity entity);

    UserJpaEntity toEntity(User domain);
}
