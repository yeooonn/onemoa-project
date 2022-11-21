package com.bitcamp.onemoaproject.dao;

import com.bitcamp.onemoaproject.vo.chat.FindChat;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface FindChatDao {

    int insert(FindChat findChat);
}
