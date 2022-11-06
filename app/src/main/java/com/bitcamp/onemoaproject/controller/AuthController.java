package com.bitcamp.onemoaproject.controller;

import com.bitcamp.onemoaproject.service.MailService;
import com.bitcamp.onemoaproject.service.MemberService;
import com.bitcamp.onemoaproject.vo.Mail;
import com.bitcamp.onemoaproject.vo.Member;
import java.util.Random;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthController {
  
  @Autowired
  MemberService memberService;
  
  @Autowired
  MailService mailService;
  
  @ResponseBody
  @PostMapping("login")
  public String login(String email, String password, String saveEmail, HttpServletRequest request, HttpServletResponse response, HttpSession session)
      throws Exception {
    Member member = memberService.get(email, password);

    if (member != null) {
      session.setAttribute("loginMember", member);
    }
    System.out.println("session.getAttribute(\"loginMember\") = " + session.getAttribute("loginMember"));

    Cookie cookie = new Cookie("email", email);
    if (saveEmail == null) {
      cookie.setMaxAge(0);
    } else {
      cookie.setMaxAge(60*60*24*7);
    }
    
    response.addCookie(cookie);

    if (member == null) {
      return "false";
    }
    return "true";
  }
  
  @ResponseBody
  @PostMapping("loginPage")
  public String loginPage(String email, String password, String saveEmail, HttpServletRequest request, HttpServletResponse response, HttpSession session)
      throws Exception {
    Member member = memberService.get(email, password);
    
    if (member != null) {
      session.setAttribute("loginMember", member);
    }
    System.out.println("session.getAttribute(\"loginMember\") = " + session.getAttribute("loginMember"));
    
    Cookie cookie = new Cookie("email", email);
    if (saveEmail == null) {
      cookie.setMaxAge(0);
    } else {
      cookie.setMaxAge(60*60*24*7);
    }
    
    response.addCookie(cookie);
    
    if (member == null) {
      return "false";
    }
    return "true";
  }
  
  @GetMapping("logout")
  public String logout(HttpSession session) throws Exception {
    session.invalidate(); // 현재 세션을 무효화시킨다.
    return "redirect:/";
  }
  
  @GetMapping("/pageLogin")
  public void loginCheck() {}
  
  @GetMapping("joinform")
  public String joinForm() {
    return "joinForm";
  }

  @ResponseBody
  @PostMapping("/nicknamecheck")
  public String nickNameCheck(String nickname) throws Exception {
    Member member = memberService.getNickName(nickname);
    if (member != null) {
      return "false";
    }
    return "true";
  }
  
  @ResponseBody
  @PostMapping("/emailauth")
  public String emailAuth(String email) throws Exception {
    
    // 이메일 중복검사 체크
    Member member = memberService.get(email);
    if (member != null) {
      return "false";
    }
    
    Random random = new Random();
    int checkNum = random.nextInt(888888) + 111111;
    
    Mail mail = new Mail();
    mail.setAddress(email);
    mail.setTitle("[onemoa] 이메일 계정 인증");
    mail.setCheckNum(checkNum);
    mail.setTemplate("emailCode");
    mailService.sendTemplateMessage(mail);
    return Integer.toString(checkNum);
  }
}
