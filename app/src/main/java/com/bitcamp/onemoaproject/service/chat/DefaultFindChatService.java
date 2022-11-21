package com.bitcamp.onemoaproject.service.chat;

import com.bitcamp.onemoaproject.dao.FindChatDao;
import com.bitcamp.onemoaproject.vo.chat.FindChat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DefaultFindChatService implements FindChatService{

    @Autowired
    FindChatDao findChatDao;

    @Override
    public List<Map<Integer, Object>> findChatRoom(FindChat findChat) {
        return findChatDao.findChatRoom(findChat);
    }
}
