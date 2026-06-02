package com.api.shortURL.auth;

import com.api.shortURL.auth.dto.LoginRequestDTO;
import com.api.shortURL.auth.dto.LoginResponseDTO;
import com.api.shortURL.auth.dto.RegisterRequestDTO;
import com.api.shortURL.auth.dto.RegisterResponseDTO;
import com.api.shortURL.auth.mapper.RegisterMapper;
import com.api.shortURL.secutiry.CustomUserDetails;
import com.api.shortURL.secutiry.TokenService;
import com.api.shortURL.user.UserService;
import com.api.shortURL.user.dto.UserRequestDTO;
import com.api.shortURL.user.dto.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserService userService;
    private final RegisterMapper mapper;

    public LoginResponseDTO login(LoginRequestDTO login){
        var usernamePassword = new UsernamePasswordAuthenticationToken(
                login.email(),
                login.password()
        );
        var auth = authenticationManager.authenticate(usernamePassword);

        if (auth.getPrincipal() == null){
            throw new RuntimeException("Erro ao fazer login");
        }

        CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
        String token = tokenService.generateToken(user);

        return new LoginResponseDTO(token);
    }

    public RegisterResponseDTO register(RegisterRequestDTO request){
        UserRequestDTO user = mapper.toUserRequestDTO(request);
        UserResponseDTO saved = userService.save(user);

        return mapper.toRegisterResponseDTO(saved);
    }
}
