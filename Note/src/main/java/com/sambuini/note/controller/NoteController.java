package com.sambuini.note.controller;

import com.sambuini.auth.entity.SessionToken;
import com.sambuini.note.dto.NoteDTO;
import com.sambuini.note.dto.NoteUpdateDTO;
import com.sambuini.note.model.Note;
import com.sambuini.note.service.NoteGroupService;
import org.hibernate.Session;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/note")
public class NoteController {

    private final NoteGroupService noteService;

    public NoteController(NoteGroupService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    private ResponseEntity<?> create(@RequestBody @Valid NoteDTO noteDTO,
                                     @SessionAttribute SessionToken sessionToken) {
        String username = sessionToken.parseUsername();

        Note note = noteService.create(noteDTO, username);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(note.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    private ResponseEntity<?> getAll(HttpSession session) {
        SessionToken sessionToken = (SessionToken) session.getAttribute(SessionToken.TOKEN);
        String username = sessionToken.parseUsername();

        return ResponseEntity.ok(noteService.findAll(username));
    }

    @DeleteMapping
    private ResponseEntity<?> delete(@RequestBody @Valid NoteDTO noteDTO,
                                     @SessionAttribute SessionToken sessionToken) {
        String username = sessionToken.parseUsername();

        noteService.delete(noteDTO, username);

        return ResponseEntity.ok().build();
    }

    @PutMapping
    private ResponseEntity<?> update(@RequestBody @Valid NoteUpdateDTO noteUpdateDTO,
                                     @SessionAttribute SessionToken sessionToken) {
        String username = sessionToken.parseUsername();

        return ResponseEntity.ok(noteService.update(noteUpdateDTO, username));
    }
}
