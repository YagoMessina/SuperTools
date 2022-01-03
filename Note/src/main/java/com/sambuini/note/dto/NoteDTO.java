package com.sambuini.note.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class NoteDTO {

    @NotNull(message = "Title cannot be null.")
    @NotBlank(message = "Title cannot be blank.")
    private String title;

    @NotNull(message = "Body cannot be null.")
    private String body;

    @NotNull(message = "Favourite cannot be null.")
    private Boolean favourite;
}
