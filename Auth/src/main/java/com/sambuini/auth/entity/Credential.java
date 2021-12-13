package com.sambuini.auth.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Credential {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Embedded
    @Column(nullable = false)
    private Password password;

    public Credential() {
    }

    public Credential(String username, String email, Password password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
