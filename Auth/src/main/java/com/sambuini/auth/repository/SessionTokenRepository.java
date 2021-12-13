package com.sambuini.auth.repository;

import com.sambuini.auth.entity.SessionToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionTokenRepository extends JpaRepository<SessionToken, Long> {
    SessionToken findByToken(String token);
}
