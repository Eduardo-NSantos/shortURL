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

    public void assertCpfNotInUse(String cpf){
        if (repository.existsByCpf(cpf)){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Cpf already in use"
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

    public UserEntity findEntity(Integer id){
        return getActiveUserOrThrow(id);
    }

    public UserResponseDTO save(UserRequestDTO request){
        this.assertEmailNotInUse(request.email());
        this.assertCpfNotInUse(request.cpf());

        UserEntity user = mapper.toEntity(request);
        UserEntity saved = repository.save(user);

        return mapper.toUserResponseDTO(saved);
    };

    public UserResponseDTO find(Integer id){
        UserEntity user = findEntity(id);
        return mapper.toUserResponseDTO(user);
    }
}
