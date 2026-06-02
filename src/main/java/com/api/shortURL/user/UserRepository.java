package com.api.shortURL.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByEmailAndDeletedAtIsNull(String email);
    Optional<UserEntity> findByIdAndDeletedAtIsNull(Integer id);
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);
}
