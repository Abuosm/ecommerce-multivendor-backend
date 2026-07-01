package com.abubakar.service;

import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {

  private final JavaMailSender javaMailSender;

  public void sendVerificationOtpEmail(String userEmail, 
    String otp,String subject,String text) throws MessagingException {
    try{
      MimeMessage mimeMessage = javaMailSender.createMimeMessage();

     MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true, "utf-8");


        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setTo(userEmail);
        mimeMessageHelper.setText(text, true);

        javaMailSender.send(mimeMessage);

    } catch (Exception e) {
      // Keep the original exception so SMTP/Authentication errors are visible in logs
      throw new MailSendException("failed to send eamil", e);
    }
    
  }
  

}
