package com.bitcamp.onemoaproject.controller.chatController;

import com.bitcamp.onemoaproject.service.chat.FindChatService;
import com.bitcamp.onemoaproject.vo.Member;
import com.bitcamp.onemoaproject.vo.chat.FindChat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/findChat/")
public class FindChatController {

    @Autowired
    FindChatService findChatService;

    @GetMapping("findChatRoom")
    public ModelAndView list(FindChat findChat, HttpSession session) {

        ModelAndView mav = new ModelAndView("/chat/findChat");

        Member member = (Member) session.getAttribute("loginMember");

        member.getNo() = findChat.getChatNo();

        System.out.println(member.getNo());

        List<Map<Integer, Object>> findRoom = findChatService.findChatRoom(findChat);
        System.out.println(findChat);

        mav.addObject("findRoom", findRoom);

        return mav;
    }
}
