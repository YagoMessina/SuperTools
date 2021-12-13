package com.sambuini.auth.repository;

import com.sambuini.auth.entity.Credential;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredentialRepository extends JpaRepository<Credential, Long> {
    Credential findByUsername(String username);
    Credential findByEmail(String email);
}
