package com.bitcamp.onemoaproject.service;

import com.bitcamp.onemoaproject.vo.QnaAttachedFile;
import com.bitcamp.onemoaproject.vo.Qna;
import com.bitcamp.onemoaproject.vo.portfolio.Portfolio;

import java.util.List;

// 비즈니스 로직을 수행하는 객체의 사용규칙(호출규칙)
//
public interface QnaService {

  void add(Qna qna) throws Exception;

  boolean update(Qna qna) throws Exception;
  boolean answerUpdate(Qna qna) throws Exception;
  Qna get(int no) throws Exception;

  boolean delete(int no) throws Exception;
  List<Qna> list() throws Exception;
  List<Qna> list(int no) throws Exception;
  QnaAttachedFile getAttachedFile(int fileNo) throws Exception;

  boolean deleteAttachedFile(int fileNo) throws Exception;

}








