package com.api.shortURL.link;

import com.api.shortURL.link.dto.LinkRequestDTO;
import com.api.shortURL.link.dto.LinkResponseDTO;
import com.api.shortURL.secutiry.CustomUserDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/link")
@RequiredArgsConstructor
public class LinkController {
    private final LinkService service;

    @PostMapping
    public ResponseEntity<LinkResponseDTO> create(
            @RequestBody @Valid LinkRequestDTO request,
            @AuthenticationPrincipal CustomUserDetails user
    ){
        LinkResponseDTO response = service.save(request, user.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
