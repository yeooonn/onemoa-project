package com.bitcamp.onemoaproject.service;

import com.bitcamp.onemoaproject.vo.qna.QnaAttachedFile;
import com.bitcamp.onemoaproject.vo.qna.Qna;
import com.bitcamp.onemoaproject.vo.paging.Criteria;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties.Credential;

// 비즈니스 로직을 수행하는 객체의 사용규칙(호출규칙)
//
public interface QnaService {

  void add(Qna qna) throws Exception;

  boolean update(Qna qna) throws Exception;
  boolean answerUpdate(Qna qna) throws Exception;
  Qna get(int no) throws Exception;

  boolean delete(int no) throws Exception;
  List<Qna> list() throws Exception;
  
  QnaAttachedFile getAttachedFile(int fileNo) throws Exception;

  boolean deleteAttachedFile(int fileNo) throws Exception;
  
  // 전체 페이징
  List<Map<String, Object>> list(Map map) throws Exception;
  
  int listCount() throws ExecutionException;
  
  // 사용자 QnA 페이징
  List<Map<String, Object>> list2(Map map) throws Exception;
  
  int listCount2(int no) throws ExecutionException;
}








