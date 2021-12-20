package com.sambuini.note.controller;

import com.sambuini.auth.entity.SessionToken;
import com.sambuini.auth.service.SessionTokenService;
import com.sambuini.error.validator.ClientValidate;
import com.sambuini.note.dto.NoteDTO;
import com.sambuini.note.model.Note;
import com.sambuini.note.service.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/note")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    private ResponseEntity<?> create(@RequestBody @Valid NoteDTO noteDTO,
                                     HttpServletRequest request) {

        //TODO rompe no hay session token
        SessionToken sessionToken = (SessionToken) request.getSession().getAttribute("token");
        String username = sessionToken.parseUsername();

        noteDTO.setUsername(username);

        Note note = noteService.create(noteDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(note.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    private ResponseEntity<?> getAll() {
        return ResponseEntity.ok(noteService.findAll());
    }
}
