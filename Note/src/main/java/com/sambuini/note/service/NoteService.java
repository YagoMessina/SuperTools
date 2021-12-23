package com.sambuini.note.service;

import com.sambuini.note.dto.NoteDTO;
import com.sambuini.note.mapper.NoteMapper;
import com.sambuini.note.model.Note;
import com.sambuini.note.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Note create(NoteDTO noteDTO, String username) {
        return noteRepository.save(NoteMapper.toModel(noteDTO, username));
    }

    public List<Note> findAll() {
        return noteRepository.findAll();
    }
}
