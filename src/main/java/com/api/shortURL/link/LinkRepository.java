package com.api.shortURL.link;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LinkRepository extends JpaRepository<LinkEntity, Integer> {
    boolean existsByShortCode(String shortCode);
    Optional<LinkEntity> findByIdAndUserId(Integer linkId, Integer userId);
}
