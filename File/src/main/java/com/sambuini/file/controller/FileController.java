package com.sambuini.file.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/file")
@CrossOrigin(origins = "*")
public class FileController {

    @GetMapping
    private String get() {
        return "File";
    }


    @PostMapping(consumes = {"multipart/form-data"})
    @CrossOrigin(origins = "*")
    public String importQuestion( @RequestParam("uploadedFileName")
                                         MultipartFile multipart, ModelMap model) {

        return "importQuestion";
    }
}
