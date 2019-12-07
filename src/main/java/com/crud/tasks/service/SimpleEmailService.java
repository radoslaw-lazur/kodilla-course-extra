package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SimpleEmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    private static  final Logger LOGGER = LoggerFactory.getLogger(SimpleMailMessage.class);

    public void send(final Mail mail) {
        LOGGER.info("Starting e-mail preparation...");
        try {
            SimpleMailMessage mailMessage = createMailMessege(mail);
            javaMailSender.send(mailMessage);
            LOGGER.info("E-mail has been sent");
        } catch (MailException e) {
            LOGGER.info("Failed to process e-mail sending: ", e.getMessage(), e);
        }
    }

    public SimpleMailMessage createMailMessege(final Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessege());
        if((mail.getToCc() == null) || (mail.getToCc().equals(""))) {
            mailMessage.setCc();
        } else {
            mailMessage.setCc(mail.getToCc());
        }
        return mailMessage;
    }
}
