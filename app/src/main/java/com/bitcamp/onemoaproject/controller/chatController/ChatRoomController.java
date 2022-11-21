package com.bitcamp.onemoaproject.controller.chatController;

import com.bitcamp.onemoaproject.service.chat.ChatService;
import com.bitcamp.onemoaproject.service.chat.FindChatService;
import com.bitcamp.onemoaproject.vo.Member;
import com.bitcamp.onemoaproject.vo.chat.ChatRoom;
import com.bitcamp.onemoaproject.vo.chat.FindChat;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/chat")
public class ChatRoomController {

    @Autowired
    ChatService chatService;

    @Autowired
    FindChatService findChatService;
//    private final ChatService chatService;

    // 채팅 리스트 화면
    @GetMapping("/room")
    public String rooms(Model model) {
        return "/chat/room";
    }
    // 모든 채팅방 목록 반환
    @GetMapping("/rooms")
    @ResponseBody
    public List<ChatRoom> room() {
        return chatService.findAllRoom();
    }
    // 채팅방 생성
    @PostMapping("/room")
    @ResponseBody
    public ChatRoom createRoom(@RequestParam String name) {
        return chatService.createRoom(name);
    }
    // 채팅방 입장 화면
    @GetMapping("/room/enter/{roomId}")
    public String roomDetail(Model model, @PathVariable String roomId) {
        //model.addAttribute("roomId", roomId);
        return "/chat/roomdetail";
    }
    // 특정 채팅방 조회
    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoom roomInfo(@PathVariable String roomId) {
        return chatService.findById(roomId);
    }

    @GetMapping("/add")
    public String add(FindChat findChat,HttpSession session, int sellerNo) throws Exception {
        Member member = (Member) session.getAttribute("loginMember");
        findChat.setBuyer(member.getNo());
        findChat.setSeller(sellerNo);
        System.out.println(findChat);
        return "redirect:add";
    }

    @PostMapping("/add")
    public String add(FindChat findChat) {
        System.out.println(findChat);
        return "redirect:room";
    }
}
