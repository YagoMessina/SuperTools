package com.sambuini.auth.service;

import com.sambuini.auth.entity.Credential;
import com.sambuini.auth.entity.SessionToken;
import com.sambuini.auth.repository.SessionTokenRepository;
import com.sambuini.error.validator.ClientValidate;
import com.sambuini.error.validator.ServerValidate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SessionTokenService {

    private final SessionTokenRepository sessionTokenRepository;

    public SessionTokenService(SessionTokenRepository sessionTokenRepository) {
        this.sessionTokenRepository = sessionTokenRepository;
    }

    public SessionToken save(Credential credential) {
        ServerValidate.notBlank(credential.getUsername(), "The username cannot be blank.");
        ServerValidate.notBlank(credential.getEmail(), "The email cannot be blank.");

        SessionToken sessionToken = new SessionToken(credential);

        return sessionTokenRepository.save(sessionToken);
    }

    public SessionToken find(String token) {
        ServerValidate.notBlank(token, "The token cannot be blank.");

        SessionToken sessionToken = sessionTokenRepository.findByToken(token);
        ClientValidate.found(sessionToken, "No session available for token: " + token);

        return sessionToken;
    }

    public void delete(Long id) {
        ServerValidate.notNull(id, "The id cannot be null.");
        Optional<SessionToken> optionalSessionToken = sessionTokenRepository.findById(id);
        if (optionalSessionToken.isPresent()) {
            sessionTokenRepository.deleteById(id);
        }
    }
}
