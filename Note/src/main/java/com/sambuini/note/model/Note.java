package com.sambuini.note.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Note {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String body;

    @Column(nullable = false)
    private String username;

    public Note() {
    }

    public Note(String title, String body, String username) {
        this.title = title;
        this.body = body;
        this.username = username;
    }
}
