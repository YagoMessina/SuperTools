package com.sambuini.auth.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sambuini.error.validator.ServerValidate;
import lombok.Data;
import net.bytebuddy.utility.RandomString;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Base64;

@Entity
@Table
@Data
public class SessionToken {

    private static final String DELIMITER = "###";

    public static final String TOKEN = "sessionToken";

    @Id
    @Column(name = "credential_id")
    private Long id;

    @Column(nullable = false)
    private String token;

    SessionToken() {
    }

    public SessionToken(Credential credential) {
        ServerValidate.notNull(credential.getId(), "The credential id cannot be null.");
        this.id = credential.getId();
        this.token = generateToken(credential.getUsername(), credential.getEmail(), LocalDateTime.now().plusDays(1));
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
