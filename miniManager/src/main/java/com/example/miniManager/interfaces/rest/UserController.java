package com.example.miniManager.interfaces.rest;

import com.example.miniManager.application.usecases.user.CreateUserUseCase;
import com.example.miniManager.application.usecases.user.GetUserUseCase;
import com.example.miniManager.domain.model.User;
import com.example.miniManager.interfaces.dto.request.UserCreateRequest;
import com.example.miniManager.interfaces.dto.response.ApiResponse;
import com.example.miniManager.interfaces.dto.response.UserResponseDTO;
import com.example.miniManager.interfaces.mapper.UserWebMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final GetUserUseCase getUserUseCase;

    public UserController(CreateUserUseCase createUserUseCase,
                          GetUserUseCase getUserUseCase) {

        this.createUserUseCase = createUserUseCase;
        this.getUserUseCase = getUserUseCase;
    }

    @PostMapping
    public ApiResponse<UserResponseDTO> createUser(
            @RequestBody UserCreateRequest request) {

        User user = createUserUseCase.execute(
                request.getFullName(),
                request.getEmail(),
                request.getPhone()
        );

        return new ApiResponse<>(
                true,
                "Create user successfully",
                UserWebMapper.toDTO(user)
        );
    }

    @GetMapping
    public ApiResponse<List<UserResponseDTO>> getUsers() {

        List<UserResponseDTO> users =
                getUserUseCase.execute()
                        .stream()
                        .map(UserWebMapper::toDTO)
                        .collect(Collectors.toList());

        return new ApiResponse<>(
                true,
                "Get users successfully",
                users
        );
    }
}