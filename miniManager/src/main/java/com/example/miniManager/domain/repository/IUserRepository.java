package com.example.miniManager.domain.repository;

import com.example.miniManager.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository {

    User save(User user);

    List<User> findAll();

    Optional<User> findByEmail(String email);

    Optional<User> findById(Long id);
}