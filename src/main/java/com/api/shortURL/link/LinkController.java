package com.api.shortURL.link;

import com.api.shortURL.link.dto.LinkRequestDTO;
import com.api.shortURL.link.dto.LinkResponseDTO;
import com.api.shortURL.secutiry.CustomUserDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/links")
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

    @DeleteMapping("/{linkId}")
    public ResponseEntity<Void> delete(
            @PathVariable Integer linkId,
            @AuthenticationPrincipal CustomUserDetails user
    ){
        service.delete(linkId, user.getId());
        return ResponseEntity.noContent().build();
    }
}
