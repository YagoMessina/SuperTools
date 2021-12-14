package com.sambuini.auth.service;

import com.sambuini.auth.entity.SessionToken;
import com.sambuini.auth.repository.SessionTokenRepository;
import com.sambuini.error.validator.ClientValidate;
import com.sambuini.error.validator.ServerValidate;
import org.springframework.stereotype.Service;

@Service
public class SessionTokenService {

    private final SessionTokenRepository sessionTokenRepository;

    public SessionTokenService(SessionTokenRepository sessionTokenRepository) {
        this.sessionTokenRepository = sessionTokenRepository;
    }

    public SessionToken save(String username, String email) {
        ServerValidate.notBlank(username, "The username cannot be blank.");
        ServerValidate.notBlank(email, "The email cannot be blank.");

        SessionToken entity = new SessionToken(username, email);
        return sessionTokenRepository.save(entity);
    }

    public SessionToken find(String token) {
        ServerValidate.notBlank(token, "The token cannot be blank.");

        SessionToken sessionToken = sessionTokenRepository.findByToken(token);
        ClientValidate.found(sessionToken, "No session available for token: " + token);

        return sessionToken;
    }
}
