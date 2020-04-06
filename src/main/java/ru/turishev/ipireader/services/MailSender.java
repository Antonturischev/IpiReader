package ru.turishev.ipireader.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.turishev.ipireader.model.User;

import java.util.Set;

@Service
public class MailSender {
    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String username;

    public void send(Set<User> mailToUsers, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(username);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        for(User user:mailToUsers) {
            if(user!=null&&!user.getEmail().isEmpty()) {
                mailMessage.setTo(user.getEmail());
               // mailSender.send(mailMessage);
            }
        }
    }
}