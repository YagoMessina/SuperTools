package com.sambuini.auth.service;

import com.sambuini.auth.entity.SessionToken;
import com.sambuini.auth.repository.SessionTokenRepository;
import org.springframework.stereotype.Service;

@Service
public class SessionTokenService {

    private final SessionTokenRepository sessionTokenRepository;

    public SessionTokenService(SessionTokenRepository sessionTokenRepository) {
        this.sessionTokenRepository = sessionTokenRepository;
    }

    public SessionToken save(String username, String email) {
        SessionToken entity = new SessionToken(username, email);
        return sessionTokenRepository.save(entity);
    }

    public boolean exists(SessionToken sessionToken) {
        return sessionTokenRepository.findByToken(sessionToken.getToken()) != null;
    }
}
