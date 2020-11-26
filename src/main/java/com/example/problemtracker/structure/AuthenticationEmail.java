package com.example.problemtracker.structure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationEmail {
    private String subject;
    private String recipient;
    private String body;
}
