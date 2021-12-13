package com.sambuini.auth.service;

import com.sambuini.auth.dto.CredentialDTO;
import com.sambuini.auth.dto.LoginDTO;
import com.sambuini.auth.entity.Credential;
import com.sambuini.auth.entity.SessionToken;
import com.sambuini.auth.mapper.CredentialMapper;
import com.sambuini.auth.repository.CredentialRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class CredentialService {

    private CredentialRepository credentialRepository;
    private SessionTokenService sessionTokenService;

    public CredentialService(CredentialRepository credentialRepository, SessionTokenService sessionTokenService) {
        this.credentialRepository = credentialRepository;
        this.sessionTokenService = sessionTokenService;
    }

    public Credential create(CredentialDTO credentialDTO) {
        Credential credential = CredentialMapper.toEntity(credentialDTO);
        return credentialRepository.save(credential);
    }

    public SessionToken loginWithUsername(String username, char[] password) {
        return login(credentialRepository.findByUsername(username), password);
    }

    public SessionToken loginWithEmail(String email, char[] password) {
        return login(credentialRepository.findByEmail(email), password);
    }

    private SessionToken login(Credential credential, char[] password) {
        if (credential == null)
            throw new RuntimeException("jasjdsajdaja");
        if (!credential.getPassword().matches(password))
            throw new RuntimeException("jajaaaaa");
        return sessionTokenService.save(credential.getUsername(), credential.getEmail());
    }
}
