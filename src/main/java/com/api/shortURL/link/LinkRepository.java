package com.api.shortURL.link;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<LinkEntity, Integer> {
    boolean existsByShortCode(String shortCode);
}
