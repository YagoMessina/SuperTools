package com.sambuini.file.controller;

import com.sambuini.auth.entity.SessionToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/file")
@CrossOrigin(origins = "*")
public class FileController {

    @GetMapping
    private String get() {
        return "File";
    }
}
