package com.zerobase.zbonlinecourseproject.components;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MailComponents {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMailTest() {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("jxxdxmxx2@gmail.com");
        msg.setSubject("안녕하세요. 제로베이스 입니다.");
        msg.setText(" 안녕하세요. 제로베이스 입니다. 방갑습니다. ");

        javaMailSender.send(msg);
    }

    public boolean sendMail(String mail, String subject, String text) {
        boolean result = false;

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(mail);
            helper.setSubject(subject);
            helper.setText(text, true);

            javaMailSender.send(message);
            result = true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return result;
    }
}
