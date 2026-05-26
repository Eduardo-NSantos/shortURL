package com.api.shortURL.link;

import com.api.shortURL.link.dto.LinkRequestDTO;
import com.api.shortURL.link.dto.LinkResponseDTO;
import com.api.shortURL.link.mapper.LinkMapper;
import com.api.shortURL.link.util.ShortCodeGenerator;
import com.api.shortURL.user.UserEntity;
import com.api.shortURL.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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

    public List<LinkResponseDTO> findAllByUser(Integer userId){
        return repository.findByUserId(userId).stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    @Transactional
    public void delete(Integer linkId, Integer userId){
        LinkEntity link = repository.findByIdAndUserId(linkId, userId).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Link not found"
                )
        );

        repository.delete(link);
    }

    public String getOriginalUrl(String shortCode){
        if (!repository.existsByShortCode(shortCode)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Link not found"
            );
        }

        LinkEntity link = repository.findByShortCode(shortCode);

        return link.getOriginalURL();
    }
}
