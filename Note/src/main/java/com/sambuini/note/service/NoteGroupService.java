package com.sambuini.note.service;

import com.sambuini.error.validator.ClientValidate;
import com.sambuini.note.dto.NoteDTO;
import com.sambuini.note.dto.NoteUpdateDTO;
import com.sambuini.note.mapper.NoteMapper;
import com.sambuini.note.model.Note;
import com.sambuini.note.model.NoteGroup;
import com.sambuini.note.repository.NoteGroupRepository;
import org.apache.catalina.mapper.Mapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class NoteGroupService {

    private final NoteGroupRepository noteGroupRepository;

    public NoteGroupService(NoteGroupRepository noteRepository) {
        this.noteGroupRepository = noteRepository;
    }

    public Note create(NoteDTO noteDTO, String username) {
        NoteGroup noteGroup = noteGroupRepository.findByUsername(username);
        if (noteGroup == null) {
            noteGroup = noteGroupRepository.save(new NoteGroup(username));
        }
        return noteGroup.addNote(NoteMapper.toModel(noteDTO));
    }

    public List<Note> findAll(String username) {
        NoteGroup noteGroup = noteGroupRepository.findByUsername(username);
        ClientValidate.found(noteGroup, "Username does not exist.");
        return noteGroup.getNotes();
    }

    public void delete(NoteDTO noteDTO ,String username) {
        NoteGroup noteGroup = noteGroupRepository.findByUsername(username);
        ClientValidate.found(noteGroup, "Username does not exist.");
        noteGroup.deleteNote(NoteMapper.toModel(noteDTO));
    }

    public Object update(NoteUpdateDTO noteUpdateDTO, String username) {
        NoteGroup noteGroup = noteGroupRepository.findByUsername(username);
        ClientValidate.found(noteGroup, "Username does not exist.");
        return noteGroup.updateNote(noteUpdateDTO);
    }
}
