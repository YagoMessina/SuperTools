package com.sambuini.auth.entity;

import javafx.util.converter.LocalDateTimeStringConverter;
import lombok.Data;

import javax.persistence.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Base64;

@Entity
@Table
@Data
public class SessionToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String token;

    private static final String DELIMITER = "###";

    SessionToken() {
    }

    public SessionToken(String username, String email) {
        this.token = generateToken(username, email,  LocalDateTime.now().plusDays(1));
    }

    public SessionToken(String token) {
        this.token = token;
    }

    private String generateToken(String username, String email, LocalDateTime expiresOn) {
        return new String(Base64.getEncoder()
                .encode((username + DELIMITER + email + DELIMITER + expiresOn).getBytes(StandardCharsets.UTF_8)));
    }

    private String getDecodedToken() {
        return new String(Base64.getDecoder().decode(token));
    }

    public String parseUsername() {
        return getDecodedToken().split(DELIMITER)[0];
    }

    public String parseEmail() {
        return getDecodedToken().split(DELIMITER)[1];
    }

    public LocalDateTime parseExpirationDate() {
        String date = getDecodedToken().split(DELIMITER)[2];
        return LocalDateTime.parse(date);
    }

    public boolean hasExpired() {
        return parseExpirationDate().isBefore(LocalDateTime.now());
    }
}
