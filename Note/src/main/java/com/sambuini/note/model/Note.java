package com.sambuini.note.model;

import com.sambuini.error.validator.ServerValidate;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@Getter
@NoArgsConstructor
public class Note {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String body;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private boolean favourite;

    public Note(String title, String body, Boolean favourite) {
        update(title, body, favourite);
        createdAt = updatedAt;
    }

    public void update(String title, String body, Boolean favourite) {
        ServerValidate.notBlank(title, "Title cannot be blank.");
        ServerValidate.notNull(body, "Body cannot be null.");
        ServerValidate.notNull(favourite, "Favourite cannot be null.");
        this.title = title;
        this.body = body;
        this.favourite = favourite;
        updatedAt = LocalDateTime.now();
    }
}
