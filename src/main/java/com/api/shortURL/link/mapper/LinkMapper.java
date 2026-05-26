package com.api.shortURL.link.mapper;

import com.api.shortURL.link.LinkEntity;
import com.api.shortURL.link.dto.LinkRequestDTO;
import com.api.shortURL.link.dto.LinkResponseDTO;
import com.api.shortURL.user.UserEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LinkMapper {
    @Value("${app.base-url}")
    String baseURL;

    public LinkEntity toEntity(LinkRequestDTO request, UserEntity user, String shortCode){
        LinkEntity link = new LinkEntity();

        link.setOriginalURL(request.originalURL());
        link.setUser(user);
        link.setShortCode(shortCode);

        return link;
    }

    public LinkResponseDTO toResponseDTO(LinkEntity entity){
        return new LinkResponseDTO(
                entity.getId(),
                entity.getOriginalURL(),
                entity.getShortCode(),
                baseURL + "/" + entity.getShortCode(),
                entity.getUser().getId()
        );
    }
}
