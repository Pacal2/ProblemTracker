package com.example.problemtracker.controller;

import com.example.problemtracker.ProblemTrackerException;
import com.example.problemtracker.datatransfer.RegisterRequest;
import com.example.problemtracker.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthorizationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody RegisterRequest registerRequest) throws ProblemTrackerException {
        authenticationService.signUp(registerRequest);
        return new ResponseEntity<>("User has been registered",
                HttpStatus.OK);

    }
}
