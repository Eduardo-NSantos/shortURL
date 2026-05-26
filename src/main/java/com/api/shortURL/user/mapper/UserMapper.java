package com.api.shortURL.user.mapper;

import com.api.shortURL.user.UserEntity;
import com.api.shortURL.user.dto.UserRequestDTO;
import com.api.shortURL.user.dto.UserResponseDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserEntity toEntity(UserRequestDTO user){
        UserEntity userEntity = new UserEntity();

        userEntity.setName(user.name());
        userEntity.setEmail(user.email());
        userEntity.setPassword(
                new BCryptPasswordEncoder().encode(user.password())
        );

        return userEntity;
    };

    public UserResponseDTO toUserResponseDTO(UserEntity user){
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt()
        );
    }
}
