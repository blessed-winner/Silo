package org.xenon.silo.archive.users.infrastructure.persistence;

import org.springframework.stereotype.Repository;
import org.xenon.silo.archive.users.domain.User;
import org.xenon.silo.archive.users.domain.UserRepository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaUserRepository implements UserRepository {
    private final UserMapper userMapper;
    private final SpringDataJpaRepository repository;

    public JpaUserRepository(UserMapper userMapper, SpringDataJpaRepository repository){
        this.userMapper = userMapper;
        this.repository = repository;
    }

    @Override
    public Optional<User> findById(UUID id){
        return repository.findById(id).map(userMapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email){
        return repository.findByEmail(email).map(userMapper::toDomain);
    }

    @Override
    public boolean existsByEmail(String email){
        return repository.existsByEmail(email);
    }

    @Override
    public User save(User user){
        UserJpaEntity entity = userMapper.toEntity(user);
        UserJpaEntity saved = repository.save(entity);
        return userMapper.toDomain(saved);
    }
}
