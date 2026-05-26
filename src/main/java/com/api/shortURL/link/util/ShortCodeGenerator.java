package com.api.shortURL.link.util;

import com.api.shortURL.link.LinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.security.SecureRandom;

@Component
@RequiredArgsConstructor
public class ShortCodeGenerator {
    private final LinkRepository linkRepository;

    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    int length = 8;
    SecureRandom random = new SecureRandom();

    public String generate() {
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }

        return sb.toString();
    }

    public String generateUniqueShortCode(){
        String code;

        for (int i = 0; i <= 20; i++){
            code = this.generate();

            if (!linkRepository.existsByShortCode(code)){
                return code;
            }
        }

        throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Failed to generate unique short code"
        );
    }
}