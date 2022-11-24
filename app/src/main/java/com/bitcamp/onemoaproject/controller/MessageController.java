package com.bitcamp.onemoaproject.controller;


import com.bitcamp.onemoaproject.service.MemberService;
import com.bitcamp.onemoaproject.service.MessageService;
import com.bitcamp.onemoaproject.vo.Member;
import com.bitcamp.onemoaproject.vo.Message;
import com.bitcamp.onemoaproject.vo.MessageAttachedFile;
import com.bitcamp.onemoaproject.vo.product.AttachedFile;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
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
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/message/")
public class MessageController {

  @Autowired
  MessageService messageService;
  
  @Autowired
  MemberService memberService;
  
  @Autowired
  ServletContext sc;
  
  // 메시지 미리보기 조회 (사이드바)
  @GetMapping("messaget")
  public String messageForm(HttpSession session, Model model) throws Exception {
    Member loginMember = (Member) session.getAttribute("loginMember");
    if(loginMember == null) {
      return "redirect:/pageLogin";
    }
    else {
      int sender = loginMember.getNo();
      model.addAttribute("member", loginMember);
      model.addAttribute("messages", messageService.list(sender));
      System.out.println("messageService.list() = " + messageService.list(sender));
      return "message/messaget";
    }
  }

  // 메시지 상세보기
  @GetMapping("messageDetail")
  public String messageList(HttpSession session, Model model, int no) throws Exception {
    System.out.println("no = " + no);
    Member loginMember = (Member) session.getAttribute("loginMember");
    if(loginMember == null) {
      return "redirect:/pageLogin";
    }
    else {
      int sender = loginMember.getNo();
      model.addAttribute("member", loginMember);
      model.addAttribute("receiverMember", memberService.get(no));
      model.addAttribute("messages", messageService.list(sender));
      model.addAttribute("details", messageService.listNo(sender, no));
      return "message/messageDetail";
    }
  }
  
  // 메시지 저장
  @PostMapping("sendContent")
  public String messageAdd(Message message, MultipartFile[] files) throws Exception {
    System.out.println("sender = " + message.getSender());
    System.out.println("receiver = " + message.getReceiver());
    System.out.println("cont = " + message.getCont());
    System.out.println("files = " + files);
    if (!message.getCont().toString().equals("")) {
      message.setMessageAttachedFiles(saveAttachedFiles(files));
      messageService.getCount(message.getSender(), message.getReceiver());
      messageService.addMessage(message);
      System.out.println("message = " + message);
      return "redirect:messageDetail?no=" + message.getReceiver();
    }
    return "redirect:messageDetail?no=" + message.getReceiver();
  }
  
  private List<MessageAttachedFile> saveAttachedFiles(MultipartFile[] files) throws IOException, ServletException {
    List<MessageAttachedFile> messageAttachedFiles = new ArrayList<>();
    String dirPath = sc.getRealPath("/message/files");
    for (MultipartFile part : files) {
      if (part.isEmpty()) {
        continue;
      }
      String filename = UUID.randomUUID().toString();
      part.transferTo(new File(dirPath + "/" + filename));
      messageAttachedFiles.add(new MessageAttachedFile(filename, filename));
    }
    return messageAttachedFiles;
  }
}
