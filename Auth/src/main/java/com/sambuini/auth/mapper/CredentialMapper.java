package com.sambuini.auth.mapper;

import com.sambuini.auth.dto.CredentialDTO;
import com.sambuini.auth.entity.Credential;
import com.sambuini.auth.entity.Password;

public class CredentialMapper {

    public static Credential toEntity (CredentialDTO credentialDTO) {
        Password password = new Password(credentialDTO.getPassword());
        return new Credential(credentialDTO.getUsername(), credentialDTO.getEmail(), password);
    }
}
