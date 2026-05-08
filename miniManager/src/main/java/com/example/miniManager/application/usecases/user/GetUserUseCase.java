package com.example.miniManager.application.usecases.user;

import com.example.miniManager.domain.model.User;
import com.example.miniManager.domain.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetUserUseCase {

    private final IUserRepository userRepository;

    public GetUserUseCase(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> execute() {
        return userRepository.findAll();
    }
}