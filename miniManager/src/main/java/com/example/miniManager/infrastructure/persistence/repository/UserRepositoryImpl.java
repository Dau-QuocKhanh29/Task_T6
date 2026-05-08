package com.example.miniManager.infrastructure.persistence.repository;

import com.example.miniManager.domain.model.User;
import com.example.miniManager.domain.repository.IUserRepository;
import com.example.miniManager.infrastructure.persistence.entity.UserDbEntity;
import com.example.miniManager.infrastructure.persistence.mapper.UserInfraMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryImpl implements IUserRepository {

    private final JpaUserRepository jpaUserRepository;

    public UserRepositoryImpl(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public User save(User user) {
        UserDbEntity entity = UserInfraMapper.toEntity(user);
        return UserInfraMapper.toDomain(jpaUserRepository.save(entity));
    }

    @Override
    public List<User> findAll() {
        return jpaUserRepository.findAll()
                .stream()
                .map(UserInfraMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaUserRepository.findByEmail(email)
                .map(UserInfraMapper::toDomain);
    }

    @Override
    public Optional<User> findById(Long id) {

        return jpaUserRepository.findById(id)
                .map(UserInfraMapper::toDomain);
    }
}