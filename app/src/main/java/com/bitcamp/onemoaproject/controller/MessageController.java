package com.bitcamp.onemoaproject.controller;


import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.bitcamp.onemoaproject.service.MemberService;
import com.bitcamp.onemoaproject.service.MessageService;
import com.bitcamp.onemoaproject.vo.Member;

@Controller
@RequestMapping("/message/")
public class MessageController {

  @Autowired
  MessageService messageService;
  @Autowired
  MemberService memberService;

  // 메시지 미리보기 조회 (사이드바)
  @GetMapping("messaget")
  public String messageForm(HttpSession session, Model model) throws Exception {
    Member loginMember = (Member) session.getAttribute("loginMember");
    int sender = loginMember.getNo();
    model.addAttribute("member", loginMember);
    model.addAttribute("messages", messageService.list(sender));
    System.out.println("messageService.list() = " + messageService.list(sender));
    return "message/messaget";
  }

  // 메시지 상세보기
  @GetMapping("messageDetail")
  public String messageList(HttpSession session, Model model, int no) throws Exception {
    System.out.println("no = " + no);
    Member loginMember = (Member) session.getAttribute("loginMember");
    int sender = loginMember.getNo();
    model.addAttribute("member", loginMember);
    model.addAttribute("receiverMember", memberService.get(no));
    model.addAttribute("messages", messageService.list(sender));
    model.addAttribute("details", messageService.listNo(sender, no));
    return "message/messageDetail";
  }

  // 메시지 저장
  @PostMapping("sendContent")
  public String messageAdd(int sender, int receiver, String contest) throws Exception {
    System.out.println("sender = " + sender);
    System.out.println("receiver = " + receiver);
    System.out.println("contest = " + contest);
    messageService.getCount(sender, receiver);
    messageService.addMessage(sender, receiver, contest);
    return "redirect:messageDetail?no=" + receiver;
  }
}
