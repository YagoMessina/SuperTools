package com.sambuini.note.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class NoteDTO {

    @NotNull(message = "Title cannot be null.")
    private String title;

    @NotNull(message = "Title cannot be body.")
    private String body;

    public NoteDTO() {
    }

    public NoteDTO(String title, String body) {
        this.title = title;
        this.body = body;
    }
}
