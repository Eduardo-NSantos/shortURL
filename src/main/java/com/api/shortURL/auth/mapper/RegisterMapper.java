package com.api.shortURL.auth.mapper;

import com.api.shortURL.auth.dto.RegisterRequestDTO;
import com.api.shortURL.auth.dto.RegisterResponseDTO;
import com.api.shortURL.user.dto.UserRequestDTO;
import com.api.shortURL.user.dto.UserResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class RegisterMapper {
    public UserRequestDTO toUserRequestDTO(RegisterRequestDTO register){
        return new UserRequestDTO(
                register.name(),
                register.cpf(),
                register.email(),
                register.password()
        );
    };

    public RegisterResponseDTO toRegisterResponseDTO(UserResponseDTO user){
        return new RegisterResponseDTO(
                user.id(),
                user.name(),
                user.email(),
                user.createdAt()
        );
    }
}
