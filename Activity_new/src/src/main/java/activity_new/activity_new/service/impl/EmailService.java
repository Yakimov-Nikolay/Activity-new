package activity_new.activity_new.service.impl;


import activity_new.activity_new.model.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.persistence.ManyToOne;
import java.time.LocalDate;


@Service
public class EmailService {


    @Autowired
    private JavaMailSender emailSender;


    public void sendSimpleMessage(String to, String subject, String text) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("Activity");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);

        System.out.println("Email send successFully..." + LocalDate.now());
    }

    public void sendMessage(String email, String username) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("Activity");
        message.setTo(email);
        message.setSubject("Have a nice day");
        message.setText(String.format("Have a nice day %s", username));

        emailSender.send(message);
        System.out.println("Emails are sent successFully..."+ LocalDate.now());
    }

}
