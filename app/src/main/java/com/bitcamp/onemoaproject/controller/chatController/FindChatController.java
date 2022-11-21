package com.bitcamp.onemoaproject.controller.chatController;

import com.bitcamp.onemoaproject.service.chat.FindChatService;
import com.bitcamp.onemoaproject.vo.Member;
import com.bitcamp.onemoaproject.vo.chat.FindChat;
import com.bitcamp.onemoaproject.vo.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
//
//@Controller
//@RequestMapping("/chat/")
//public class FindChatController {
//
//    @Autowired
//    FindChatService findChatService;
//
//    @PostMapping("add")
//    public String add(FindChat findChat, HttpSession session, int sellerNo) throws Exception {
//        Member member = (Member) session.getAttribute("loginMember");
//        findChat.setBuyer(member.getNo());
//        findChat.setSeller(sellerNo);
//        return "redirect:/";
//    }
//}
