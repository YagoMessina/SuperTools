package com.sambuini.note.model;

import com.sambuini.error.validator.ClientValidate;
import com.sambuini.error.validator.ServerValidate;
import com.sambuini.note.dto.NoteUpdateDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table
public class NoteGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Note> notes;

    public NoteGroup() {
    }

    public NoteGroup(String username) {
        ServerValidate.notBlank(username, "Username cannot be blank.");
        this.username = username;
        notes = new ArrayList<>();
    }

    public Note addNote(Note note) {
        ServerValidate.notNull(note, "Note cannot be blank.");

        boolean titleDoesNotExist = notes.stream().noneMatch(n -> n.getTitle().equals(note.getTitle()));
        ClientValidate.isTrue(titleDoesNotExist, "Title already exists on another note.");

        notes.add(note);
        return note;
    }

    public void deleteNote(Note note) {
        ServerValidate.notNull(note, "Note cannot be blank.");
        ServerValidate.isTrue(notes.contains(note), "Note does not exist.");
        notes.remove(note);
    }

    public List<Note> getNotes() {
        return Collections.unmodifiableList(notes);
    }


    public Note updateNote(NoteUpdateDTO updatedNote) {
        ServerValidate.notNull(updatedNote, "Updated note cannot be null.");
        Long id = updatedNote.getId();
        ServerValidate.notNull(id, "Id cannot be null.");
        Note note = notes.stream().filter(n -> n.getId().equals(id)).findFirst().orElse(null);
        ClientValidate.found(note, "Note " + id + " does not exist.");
        note.update(updatedNote.getTitle(), updatedNote.getBody(), updatedNote.getFavourite());
        return note;
    }
}
