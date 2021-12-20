package com.sambuini.note.mapper;

import com.sambuini.note.dto.NoteDTO;
import com.sambuini.note.model.Note;

public class NoteMapper {

    public static Note toModel(NoteDTO noteDTO) {
        return new Note(noteDTO.getTitle(), noteDTO.getBody(), noteDTO.getUsername());
    }
}
