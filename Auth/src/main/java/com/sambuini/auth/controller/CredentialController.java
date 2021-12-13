package com.sambuini.auth.controller;

import com.sambuini.auth.dto.CredentialDTO;
import com.sambuini.auth.dto.LoginDTO;
import com.sambuini.auth.service.CredentialService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class CredentialController {

    private CredentialService credentialService;

    public CredentialController(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @PostMapping
    private ResponseEntity<?> create(@RequestBody @Valid CredentialDTO credentialDTO) {
        credentialService.create(credentialDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    private ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        boolean isUsernameBlank = StringUtils.isBlank(loginDTO.getUsername());
        boolean isEmailBlank = StringUtils.isBlank(loginDTO.getEmail());
        if(isUsernameBlank && isEmailBlank)
            throw new RuntimeException("jaja");
        if(!isUsernameBlank)
            return ResponseEntity.ok(credentialService
                    .loginWithUsername(loginDTO.getUsername(), loginDTO.getPassword()));
        else
            return ResponseEntity.ok(credentialService
                    .loginWithEmail(loginDTO.getEmail(), loginDTO.getPassword()));
    }


}
