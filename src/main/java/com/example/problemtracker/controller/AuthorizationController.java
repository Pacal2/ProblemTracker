package com.example.problemtracker.controller;

import com.example.problemtracker.datatransfer.RegisterRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthorizationController {

    @PostMapping("/signup")
    public void signUp(@RequestBody RegisterRequest registerRequest) {

    }
}
