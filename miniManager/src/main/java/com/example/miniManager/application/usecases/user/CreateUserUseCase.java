package com.example.miniManager.application.usecases.user;

import com.example.miniManager.domain.model.User;
import com.example.miniManager.domain.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

@Service
public class CreateUserUseCase {

    private final IUserRepository userRepository;

    public CreateUserUseCase(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(String fullName, String email, String phone) {

        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        if (!Pattern.matches(emailRegex, email)) {
            throw new RuntimeException("Email không đúng format");
        }

        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email đã tồn tại");
        }

        User user = new User();
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPhone(phone);
        user.setCreatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }
}