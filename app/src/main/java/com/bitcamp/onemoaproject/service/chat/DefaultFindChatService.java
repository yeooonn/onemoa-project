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
    public void add(FindChat findChat) throws Exception {
        if (findChatDao.insert(findChat) == 0) {
            throw new Exception("채팅방 개설 실패!");
        } else {
            findChatDao.insert(findChat);
        }
    }
}
