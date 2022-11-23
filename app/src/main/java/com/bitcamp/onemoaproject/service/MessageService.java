package com.bitcamp.onemoaproject.service;

import com.bitcamp.onemoaproject.vo.Message;
import java.util.List;

public interface MessageService {
  // 메시지 미리보기 조회 (사이드바)
  List<Message> list(int sender) throws Exception;
  
  // 메시지 상세보기
  List<Message> listNo(int sender, int no) throws Exception;
  
  // 메시지 저장
  void addMessage(Message message) throws Exception;
  
  // 메시지 카운터
  int getCount(int sender, int receiver);
}








