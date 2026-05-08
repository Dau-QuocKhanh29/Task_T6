package com.example.miniManager.infrastructure.persistence.mapper;


import com.example.miniManager.domain.model.User;
import com.example.miniManager.infrastructure.persistence.entity.UserDbEntity;

public class UserInfraMapper {

    public static UserDbEntity toEntity(User user) {
        UserDbEntity entity = new UserDbEntity();

        entity.setId(user.getId());
        entity.setFullName(user.getFullName());
        entity.setEmail(user.getEmail());
        entity.setPhone(user.getPhone());
        entity.setCreatedAt(user.getCreatedAt());

        return entity;
    }

    public static User toDomain(UserDbEntity entity) {
        return new User(
                entity.getId(),
                entity.getFullName(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getCreatedAt()
        );
    }
}