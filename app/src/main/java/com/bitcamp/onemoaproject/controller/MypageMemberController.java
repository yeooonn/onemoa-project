package com.bitcamp.onemoaproject.controller.mypageController;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import com.bitcamp.onemoaproject.service.MemberService;
import com.bitcamp.onemoaproject.vo.Member;

@Controller
@RequestMapping("mypage")
public class MypageMemberController {

  ServletContext sc;
  MemberService memberService;

  public MypageMemberController(MemberService memberService, ServletContext sc) {
    System.out.println("MemberController() 호출됨!");
    this.memberService = memberService;
    this.sc = sc;
  }

  @GetMapping("changepw")
  public void changepw() throws Exception {
  }

  @PostMapping("checkpassword")
  public String checkpassword(String password, String newPassword, 
      String newPasswordConfirm, HttpSession session) throws Exception {

    // 1. 현재 비밀번호 맞는지 체크
    Member loginMember = (Member) session.getAttribute("loginMember");
    String email = loginMember.getEmail();
    Member member = memberService.get(loginMember.getNo());

    // boolean isPasswdRight = BCrypt.checkpw(password, member.getPassword());

    // System.out.println(isPasswdRight);

    //    if(!password.equals(member.getPassword())) { // 현제 비밀번호가 일치하기않으면
    //      throw new Exception("현제 패스워드가 일치하지 않습니다.");
    //    }

    // 2. 새 비밀번호, 새비밀번호 확인 맞는지 체크
    if (newPassword.equals(newPasswordConfirm) == false) { // 새 비밀번호와 새 비밀번호 확인이 일치하기않으면
      throw new Exception("새 비밀번호와 새 비밀번호 확인이 서로 일치하지 않습니다.");
    }

    // 3. DB 비밀번호 변경
    memberService.modifyPasswd(email, newPassword);

    return "redirect:changepwResult"; 

  }

  @GetMapping("changepwResult")
  public void changepwResult(HttpSession session) throws Exception {
    session.invalidate(); // 현재 세션을 무효화시킨다.
  }

  @GetMapping("myinfoResult")
  public void myinfoResult() throws Exception {
  }

  @GetMapping("leave")
  public void leave() throws Exception {
  }

  @GetMapping("leaveHere")
  public String leaveHere(HttpSession session) throws Exception {
    Member loginMember = (Member) session.getAttribute("loginMember");
    memberService.updateStatus(loginMember.getEmail());

    return "redirect:leaveResult";
  }

  @GetMapping("leaveResult")
  public void leaveResult(HttpSession session) throws Exception {
    session.invalidate(); // 현재 세션을 무효화시킨다.
  }



  @GetMapping("myinfo")
  public void myinfo(HttpSession session, Model model) throws Exception {
    Member loginMember = (Member) session.getAttribute("loginMember");
    model.addAttribute("member", memberService.get(loginMember.getNo()));
    model.getAttribute("member");
    System.out.println(model.getAttribute("member"));

  }

  @PostMapping("myinfoUpdate")
  public String myinfoUpdate(Member member, MultipartFile files, HttpSession session) throws Exception {

    member.setProfile(saveProfile(files));

    if (!memberService.myinfoUpdate(member)) {
      throw new Exception("회원 변경 오류입니다!");
    }

    return "redirect:myinfoResult";
  }

  private String saveProfile(MultipartFile files)
      throws IOException, ServletException {
    String dirPath = sc.getRealPath("/member/files");

    if(files.getSize() != 0) {
      String filename = UUID.randomUUID().toString();
      files.transferTo(new File(dirPath + "/" + filename));

      return filename;
    }
    return null;
  }


}






























