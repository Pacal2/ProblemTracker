package com.example.problemtracker.service;

import com.example.problemtracker.ProblemTrackerException;
import com.example.problemtracker.structure.AuthenticationEmail;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.management.Notification;

@Service
@AllArgsConstructor
@Slf4j
public class MailService {

    private JavaMailSender mailSender;
    private MailContentCreator mailContentCreator;

    public void sendMail(AuthenticationEmail authenticationEmail) throws ProblemTrackerException {
        MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom("problemtracker2020@gmail.com");
            mimeMessageHelper.setTo(authenticationEmail.getRecipient());
            mimeMessageHelper.setSubject(authenticationEmail.getSubject());
            mimeMessageHelper.setText(mailContentCreator.build(authenticationEmail.getBody()));
        };
        try {
            mailSender.send(mimeMessagePreparator);
            log.info("Authentication email has been sent.");
        } catch (MailException e) {
            throw new ProblemTrackerException("Error while sending email to " + authenticationEmail.getRecipient());
        }

    }
}
