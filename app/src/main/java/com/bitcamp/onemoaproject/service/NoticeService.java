package com.bitcamp.onemoaproject.service;

import com.bitcamp.onemoaproject.vo.paging.Criteria;
import java.util.List;
import com.bitcamp.onemoaproject.vo.Notice;
import java.util.Map;
import java.util.concurrent.ExecutionException;

// 비즈니스 로직을 수행하는 객체의 사용규칙 (호출규칙)
// 
public interface NoticeService {

  void add(Notice notice) throws Exception;

  boolean update(Notice notice) throws Exception;

  Notice get(int no) throws Exception;

  boolean delete(int no) throws Exception;

  List<Notice> list() throws Exception;
  
  // 페이징
  List<Map<String, Object>> list(Criteria cri) throws Exception;
  
  // 공지사항의 글의 개수 조회
  int listCount() throws ExecutionException;
  
  //  AttachedFile getAttachedFile(int fileNo) throws Exception;

  //  boolean deleteAttachedFile(int fileNo) throws Exception;
}
