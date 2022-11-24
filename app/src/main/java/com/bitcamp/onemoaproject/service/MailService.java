package com.bitcamp.onemoaproject.service;

import com.bitcamp.onemoaproject.vo.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {
  
  @Autowired
  private JavaMailSender emailSender;
  
  @Autowired
  private SpringTemplateEngine templateEngine;
  
  public void sendTemplateMessage(Mail mail) throws MessagingException {
    MimeMessage message = emailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
    //메일 제목 설정
    helper.setSubject(mail.getTitle());
    
    //수신자 설정
    helper.setTo(mail.getAddress());
    
    //참조자 설정
    //helper.setCc(mail.getCcAddress());
    
    String email = mail.getAddress();
    String checkNum = String.valueOf(mail.getCheckNum());
    String templates = mail.getTemplate();
    
    //템플릿에 전달할 데이터 설정
    Context context = new Context();
    context.setVariable("email", email);
    context.setVariable("checkNum", checkNum);
    
    //메일 내용 설정 : 템플릿 프로세스
    String html = templateEngine.process(templates, context);
    helper.setText(html, true);
   
    //메일 보내기
    emailSender.send(message);
  }
}

