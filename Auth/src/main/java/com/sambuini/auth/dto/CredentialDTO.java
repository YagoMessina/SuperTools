package com.sambuini.auth.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CredentialDTO {

    @NotNull(message = "Username cannot be null.")
    private String username;

    @NotNull(message = "Email cannot be null.")
    private String email;

    @NotNull(message = "Password cannot be null.")
    private char[] password;
}
