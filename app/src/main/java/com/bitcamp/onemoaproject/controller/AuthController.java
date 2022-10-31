package com.bitcamp.onemoaproject.controller;

import java.util.Random;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bitcamp.onemoaproject.service.MemberService;
import com.bitcamp.onemoaproject.vo.Member;

@Controller
public class AuthController {
  MemberService memberService;

  public AuthController(MemberService memberService) {
    this.memberService = memberService;
  }

  @ResponseBody
  @PostMapping("login")
  public String login(String email, String password, String saveEmail, HttpServletRequest request,
      Model model, HttpServletResponse response, HttpSession session) throws Exception {
    Member member = memberService.get(email, password);

    if (member != null) {
      session.setAttribute("loginMember", member);
    }

    Cookie cookie = new Cookie("email", email);
    if (saveEmail == null) {
      cookie.setMaxAge(0);
    } else {
      cookie.setMaxAge(60 * 60 * 24 * 7);
    }

    response.addCookie(cookie);

    model.addAttribute("member", member);

    if (member == null) {
      return "false";
    }
    return "true";
  }

  @GetMapping("logout")
  public String logout(HttpSession session) throws Exception {
    session.invalidate(); // 현재 세션을 무효화시킨다.
    return "redirect:./";
  }

  @ResponseBody
  @PostMapping("/nicknamecheck")
  public String nickNameCheck(String nickname) throws Exception {
    Member member = memberService.getNickName(nickname);
    System.out.println(member);
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

    /* 이메일 보내기 */
    String setFrom = "자신의 이메일을 입력해주세요";
    String toMail = email;
    String title = "회원가입 인증 이메일 입니다.";
    String content = "홈페이지를 방문해주셔서 감사합니다." + "<br><br>" + "인증 번호는 " + checkNum + "입니다." + "<br>"
        + "해당 인증번호를 인증번호 확인란에 기입하여 주세요.";
    System.out.println(toMail);
    //    try {
    //      MimeMessage message = mailSender.createMimeMessage();
    //      MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
    //      helper.setFrom(setFrom);
    //      helper.setTo(toMail);
    //      helper.setSubject(title);
    //      helper.setText(content,true);
    //      mailSender.send(message);
    //    }catch(Exception e) {
    //      e.printStackTrace();
    //    }
    System.out.println(Integer.toString(checkNum));
    return Integer.toString(checkNum);
  }
}
