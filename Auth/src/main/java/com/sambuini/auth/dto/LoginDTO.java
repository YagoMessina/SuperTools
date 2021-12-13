package com.sambuini.auth.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginDTO {

    private String username;

    private String email;

    @NotNull(message = "Password cannot be null")
    private char[] password;

}
