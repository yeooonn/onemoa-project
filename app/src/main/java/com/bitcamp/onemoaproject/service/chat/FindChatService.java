package com.bitcamp.onemoaproject.service.chat;

import com.bitcamp.onemoaproject.vo.chat.FindChat;

import java.util.List;
import java.util.Map;

public interface FindChatService {
    List<Map<Integer, Object>> findChatRoom(FindChat findChat);
}
