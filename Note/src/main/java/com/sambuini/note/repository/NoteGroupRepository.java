package com.sambuini.note.repository;

import com.sambuini.note.model.NoteGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NoteGroupRepository extends JpaRepository<NoteGroup, Long> {
    NoteGroup findByUsername(String username);
}
