package com.example.problemtracker.service;

import com.example.problemtracker.ProblemTrackerException;
import com.example.problemtracker.structure.AuthenticationEmail;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.management.Notification;

@Service
@AllArgsConstructor
@Slf4j
public class MailService {

    private final JavaMailSender mailSender;
    private final MailContentCreator mailContentCreator;

    @Async
    public void sendMail(AuthenticationEmail authenticationEmail){
        MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom("problemtracker2020@email.com");
            mimeMessageHelper.setTo(authenticationEmail.getRecipient());
            mimeMessageHelper.setSubject(authenticationEmail.getSubject());
            mimeMessageHelper.setText(mailContentCreator.build(authenticationEmail.getBody()));
        };
        try {
            mailSender.send(mimeMessagePreparator);
            log.info("Authentication email has been sent.");
        } catch (MailException e) {
            log.error("Exception occured when sending mail", e);
            throw new ProblemTrackerException("Error while sending email to " + authenticationEmail.getRecipient(), e);
        }

    }
}
