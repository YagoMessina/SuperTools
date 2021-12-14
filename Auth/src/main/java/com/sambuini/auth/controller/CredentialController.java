package com.sambuini.auth.controller;

import com.sambuini.auth.dto.CredentialDTO;
import com.sambuini.auth.dto.LoginDTO;
import com.sambuini.auth.service.CredentialService;
import com.sambuini.error.validator.ClientValidate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class CredentialController {

    private final CredentialService credentialService;

    public CredentialController(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    private static final String MYSTIC_CODE = "ACM1PT";

    private static final String ACCESS_HEADER = "mysticCode";

    @PostMapping
    private ResponseEntity<?> create(@RequestBody @Valid CredentialDTO credentialDTO, HttpServletRequest request) {
        String mysticCode = request.getHeader(ACCESS_HEADER);
        ClientValidate.notBlank(mysticCode, "Access denied.");
        ClientValidate.isEqual(mysticCode, MYSTIC_CODE, "Access denied.");
        credentialService.create(credentialDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    private ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        boolean isUsernameBlank = StringUtils.isBlank(loginDTO.getUsername());
        boolean isEmailBlank = StringUtils.isBlank(loginDTO.getEmail());

        ClientValidate.isTrue(!(isUsernameBlank && isEmailBlank), "Missing username or email.");

        if(!isUsernameBlank) {
            return ResponseEntity.ok(credentialService
                .loginWithUsername(loginDTO.getUsername(), loginDTO.getPassword()));
        } else {
            return ResponseEntity.ok(credentialService
                .loginWithEmail(loginDTO.getEmail(), loginDTO.getPassword()));
        }
    }


}
