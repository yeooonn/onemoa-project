package com.bitcamp.onemoaproject.controller;

import com.bitcamp.onemoaproject.service.MailService;
import com.bitcamp.onemoaproject.service.MemberService;
import com.bitcamp.onemoaproject.vo.Mail;
import com.bitcamp.onemoaproject.vo.Member;
import java.util.Map;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/member/")
//- 애노테이션을 붙일 때 객체 이름을 명시하면 그 이름으로 저장한다.
//- 프론트 컨트롤러는 페이지 컨트로러를 찾을 때 이 이름으로 찾을 것이다.
public class MemberController {
  
  @Autowired
  MemberService memberService;
  
  @Autowired
  MailService mailService;
  
  // 회원 등록
  @PostMapping("add")
  public String add(Member member) throws Exception {
    memberService.add(member);
    return "redirect:../";
  }
  
  // 아이디 찾기 / 패스워드 초기화 폼
  @GetMapping("findIdPw")
  public void findIdPw() {}
  
  // 아이디 찾기
  @ResponseBody
  @PostMapping("findById")
  public String memberfindById(String name, String email) throws Exception {
    Member member = memberService.getIdEmail(name, email);
    System.out.println("member = " + member);
    if (member == null) {
      return "false";
    }
    return email;
  }
  
  // 패스워드 초기화전 (사용자 인증)
  @ResponseBody
  @PostMapping("findByPwd")
  public String memberfindByPwd(String name, String email) throws Exception {
    Member member = memberService.getIdEmail(name, email);
    if(member != null) {
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
    return "false";
  }
  
  // 패스워드 초기화
  @ResponseBody
  @PostMapping("resetPassword")
  public String memberfindByPwd(String name, String email, String emailCodeKey) throws Exception {
    Member member = memberService.getIdEmail(name, email);
    if(member != null) {
      member.setPassword(emailCodeKey);
      System.out.println("member = " + member);
      if (!memberService.updatePwd(member)) {
        throw new Exception("사용자 변경 실패!");
      }

      Mail mail = new Mail();
      mail.setAddress(email);
      mail.setTitle("[onemoa] 계정 패스워드 초기화");
      mail.setCheckNum(Integer.parseInt(emailCodeKey));
      mail.setTemplate("resetPassword");
      mailService.sendTemplateMessage(mail);
      return "true";
    }
    return "false";
  }
  
  @GetMapping("form")
  public void form() throws Exception {
  }
  
  @GetMapping("list")
  public void list(Model model) throws Exception {
    // 프론트 컨트롤러가 건네준 Model 객체에 작업 결과를 담아 두면
    // 핸들러 호출이 끝났을 때 JSP를 실행하기 전에
    // 먼저 Model 객체에 담아둔 값을 ServletRequest 보관소로 옮긴다.
    model.addAttribute("members", memberService.list());
  }
  
  @GetMapping("detail")
  public void detail(int no, Map map) throws Exception {
    
    Member member = memberService.get(no);
    
    if (member == null) {
      throw new Exception("사용자 조회 실패!");
    }
    
    map.put("member", member);
  }
  
  @PostMapping("update")
  public String update(Member member) throws Exception {
    
    if (!memberService.update(member)) {
      throw new Exception("사용자 변경 실패!");
    }
    
    return "redirect:list";
  }
  
  @GetMapping("delete")
  public String delete(int no) throws Exception {
    if (!memberService.delete(no)) {
      throw new Exception("회원 삭제 오류입니다!");
    }
    
    return "redirect:list";
  }
}
