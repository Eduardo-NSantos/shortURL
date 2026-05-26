package com.api.shortURL.link;

import com.api.shortURL.link.dto.LinkRequestDTO;
import com.api.shortURL.link.dto.LinkResponseDTO;
import com.api.shortURL.link.mapper.LinkMapper;
import com.api.shortURL.link.util.ShortCodeGenerator;
import com.api.shortURL.user.UserEntity;
import com.api.shortURL.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LinkService {
    private final LinkRepository repository;
    private final LinkMapper mapper;
    private final UserService userService;
    private final ShortCodeGenerator shortCodeGenerator;

    public LinkResponseDTO save(LinkRequestDTO request, Integer userId){
        UserEntity user = userService.findEntity(userId);

        LinkEntity link = mapper.toEntity(request, user, shortCodeGenerator.generateUniqueShortCode());
        LinkEntity saved = repository.save(link);

        return mapper.toResponseDTO(saved);
    }
}
