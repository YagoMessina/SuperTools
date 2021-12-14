package com.sambuini.auth.service;

import com.sambuini.auth.dto.CredentialDTO;
import com.sambuini.auth.entity.Credential;
import com.sambuini.auth.entity.SessionToken;
import com.sambuini.auth.mapper.CredentialMapper;
import com.sambuini.auth.repository.CredentialRepository;
import com.sambuini.error.validator.ClientValidate;
import org.springframework.stereotype.Service;

@Service
public class CredentialService {

    private final CredentialRepository credentialRepository;
    private final SessionTokenService sessionTokenService;

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
        ClientValidate.found(credential, "No credentials found.");
        ClientValidate.isTrue(credential.getPassword().matches(password),"The password is incorrect.");

        sessionTokenService.delete(credential.getId());

        return sessionTokenService.save(credential);
    }
}
