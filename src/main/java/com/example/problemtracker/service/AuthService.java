package com.example.problemtracker.service;

import com.example.problemtracker.ProblemTrackerException;
import com.example.problemtracker.repository.UsersRepository;
import com.example.problemtracker.repository.VerificationCodeRepository;
import com.example.problemtracker.structure.AuthenticationEmail;
import com.example.problemtracker.structure.User;
import com.example.problemtracker.structure.VerificationCode;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.problemtracker.datatransfer.RegisterRequest;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UsersRepository usersRepository;
    private final VerificationCodeRepository verificationCodeRepository;
    private final MailService mailService;

    @Transactional
    public void signup(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setDate(Instant.now());
        user.setAuthenticated(false);

        usersRepository.save(user);

        String code = createVerificationCode(user);
        mailService.sendMail(new AuthenticationEmail(
                "Please Activate your Account",
                user.getEmail(), "Thank you for signing up to Spring Reddit, " +
                "please click on the below url to activate your account : " +
                "http://localhost:8080/api/auth/accountVerification/" + code));
    }

    private String createVerificationCode(User user) {
        String token  = UUID.randomUUID().toString();
        VerificationCode verificationToken = new VerificationCode();
        verificationToken.setToken(token);
        verificationToken.setUser(user);

        verificationCodeRepository.save(verificationToken);
        return token;
    }
}
