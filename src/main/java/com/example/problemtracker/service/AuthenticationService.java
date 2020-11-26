package com.example.problemtracker.service;

import com.example.problemtracker.repository.UsersRepository;
import com.example.problemtracker.repository.VerificationCodeRepository;
import com.example.problemtracker.structure.Users;
import com.example.problemtracker.structure.VerificationCode;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.problemtracker.datatransfer.RegisterRequest;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Random;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final VerificationCodeRepository verificationCodeRepository;

    @Transactional
    public void signUp(RegisterRequest registerRequest) {
        Users user = new Users();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());
        user.setDate(Instant.now());
        user.setAuthenticated(false);

        usersRepository.save(user);

        createVerificationCode(user);
    }

    private String createVerificationCode(Users user) {
        String code = UUID.randomUUID().toString();
        VerificationCode verificationToken = new VerificationCode();
        verificationToken.setCode(code);
        verificationToken.setUser(user);

        verificationCodeRepository.save(verificationToken);
        return code;
    }
}
