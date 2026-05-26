package com.api.shortURL.link;

import com.api.shortURL.user.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "links")
@Setter
@Getter
public class LinkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, name = "original_url")
    private String originalURL;

    @Column(nullable = false, unique = true, name = "short_code")
    private String shortCode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
