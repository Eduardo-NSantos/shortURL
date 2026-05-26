package com.api.shortURL.user;

import com.api.shortURL.user.dto.UserRequestDTO;
import com.api.shortURL.user.dto.UserResponseDTO;
import com.api.shortURL.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final UserMapper mapper;

    public void assertEmailNotInUse(String email){
        if (repository.existsByEmail(email)){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Email already in use"
            );
        }
    }

    public UserEntity getActiveUserOrThrow(Integer id){
        return repository.findByIdAndDeletedAtIsNull(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User not found"
                )
        );
    }

    public UserResponseDTO save(UserRequestDTO request){
        this.assertEmailNotInUse(request.email());

        UserEntity user = mapper.toEntity(request);
        UserEntity saved = repository.save(user);

        return mapper.toUserResponseDTO(saved);
    };

    public UserResponseDTO find(Integer id){
        UserEntity user = getActiveUserOrThrow(id);

        return mapper.toUserResponseDTO(user);
    }
}
