package com.sambuini.note.mapper;

import com.sambuini.note.dto.NoteDTO;
import com.sambuini.note.model.Note;

public class NoteMapper {

    public static Note toModel(NoteDTO noteDTO, String username) {
        return new Note(noteDTO.getTitle(), noteDTO.getBody(), username);
    }
}
