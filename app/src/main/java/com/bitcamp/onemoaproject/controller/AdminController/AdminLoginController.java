package com.bitcamp.onemoaproject.controller.AdminController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.bitcamp.onemoaproject.service.AdminMemberService;
import com.bitcamp.onemoaproject.vo.AdminMember;

@Controller
@RequestMapping("admin/")
public class AdminLoginController {

  @Autowired
  AdminMemberService adaminMemberService;

  @GetMapping("/adminLogin")
  public void adminLogin() {}

  @PostMapping("adminlogin")
  public String adminlogin(String email, String password, HttpServletRequest request, HttpServletResponse response, HttpSession session)
      throws Exception {
    AdminMember adaminMember = adaminMemberService.get(email, password);

    if (adaminMember != null) {
      session.setAttribute("loginAdminMember", adaminMember);
    }
    System.out.println("session.getAttribute(\"loginAdminMember\") = " + session.getAttribute("loginAdminMember"));

    if (adaminMember == null) {
      return "false";
    }
    return "redirect:member/memberList";
  }

  //
  //  @GetMapping("logout")
  //  public String logout(HttpSession session) throws Exception {
  //    session.invalidate(); // 현재 세션을 무효화시킨다.
  //    return "redirect:/";
  //  }

}
