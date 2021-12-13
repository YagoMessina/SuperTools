package com.sambuini.auth.controller;

import com.sambuini.auth.entity.SessionToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/get")
    public String get (HttpSession session) {
        return ((SessionToken)session.getAttribute("token")).parseEmail();
    }
}
