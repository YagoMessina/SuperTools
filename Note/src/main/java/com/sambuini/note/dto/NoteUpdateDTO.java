package com.sambuini.note.dto;

import javax.validation.constraints.NotNull;

public class NoteUpdateDTO extends NoteDTO {

    @NotNull(message = "Id cannot be null.")
    private Long id;

    public Long getId() {
        return id;
    }
}
