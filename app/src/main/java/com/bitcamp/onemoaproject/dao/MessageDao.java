package com.bitcamp.onemoaproject.dao;

import com.bitcamp.onemoaproject.vo.Message;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MessageDao {
  // 메시지 미리보기 조회 (사이드바)
  List<Message> findAll(int sender);
  
  // 메시지 상세보기
  List<Message> findByNo(@Param("sender") int sender, @Param("no") int no);
  
  // 메시지 저장(객체)
  int insertMessage(Message message);
  
  // 메시지 파일 저장
  int insertMessageFiles(Message message);
  
  // 메시지 카운터
  int messageCount(@Param("sender") int sender, @Param("receiver") int receiver);
  
  // 메시지 빈값 저장
  int insertMessage2(@Param("sender") int sender, @Param("receiver") int receiver);
}
