package com.example.miniManager.interfaces.mapper;

import com.example.miniManager.domain.model.User;
import com.example.miniManager.interfaces.dto.response.UserResponseDTO;

public class UserWebMapper {

    public static UserResponseDTO toDTO(User user) {

        UserResponseDTO dto = new UserResponseDTO();

        dto.setId(user.getId());
        dto.setFullName(user.getFullName());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setCreatedAt(user.getCreatedAt());

        return dto;
    }
}