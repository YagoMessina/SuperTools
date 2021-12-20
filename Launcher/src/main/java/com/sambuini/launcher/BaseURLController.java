package com.sambuini.launcher;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class BaseURLController {

    @GetMapping
    public String getBasePage() {
        return "SuperTools";
    }
}
