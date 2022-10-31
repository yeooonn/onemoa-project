package com.bitcamp.onemoaproject.service;

import java.util.List;
import com.bitcamp.onemoaproject.vo.Notice;

// 비즈니스 로직을 수행하는 객체의 사용규칙 (호출규칙)
// 
public interface NoticeService {

  void add(Notice notice) throws Exception;

  boolean update(Notice notice) throws Exception;

  Notice get(int no) throws Exception;

  boolean delete(int no) throws Exception;

  List<Notice> list() throws Exception;

  //  AttachedFile getAttachedFile(int fileNo) throws Exception;

  //  boolean deleteAttachedFile(int fileNo) throws Exception;
}
