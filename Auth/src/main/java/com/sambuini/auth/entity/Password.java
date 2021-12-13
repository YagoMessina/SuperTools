package com.sambuini.auth.entity;

import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Embeddable;
import java.security.SecureRandom;
import java.util.Arrays;

@Embeddable
public class Password {

    private static final int STRENGTH = 10;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(STRENGTH, new SecureRandom());

    private String password;

    Password() {
    }

    public Password(char[] rawPassword) {
        this.password = encode(rawPassword);
        Arrays.fill(rawPassword, ' ');
    }

    private String encode(char[] rawPassword) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(rawPassword);
        String encodedPassword = encoder.encode(stringBuilder);
        stringBuilder.delete(0, rawPassword.length);
        return encodedPassword;
    }

    public boolean matches(char[] rawPassword) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(rawPassword);
        return encoder.matches(stringBuilder, password);
    }
}
