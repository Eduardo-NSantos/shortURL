package com.api.shortURL.user;

import com.api.shortURL.secutiry.CustomUserDetails;
import com.api.shortURL.user.dto.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> find(
            @AuthenticationPrincipal
            CustomUserDetails user
    ){
        UserResponseDTO response = service.find(user.getId());
        return ResponseEntity.ok(response);
    }
}
