package com.bitcamp.onemoaproject.service;


import com.bitcamp.onemoaproject.dao.FaqDao;
import com.bitcamp.onemoaproject.vo.Faq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultFaqService implements FaqService {

  @Autowired
  FaqDao faqDao;

//  @Transactional
//  @Override
//  public void add(Board board) throws Exception {
//    // 1) 게시글 등록
//    if (boardDao.insert(board) == 0) {
//      throw new Exception("게시글 등록 실패!");
//    }
//
//    // 2) 첨부파일 등록
//    if (board.getAttachedFiles().size() > 0) {
//      boardDao.insertFiles(board);
//    }
//  }
//
//  @Transactional
//  @Override
//  public boolean update(Board board) throws Exception {
//    // 1) 게시글 변경
//    if (boardDao.update(board) == 0) {
//      return false;
//    }
//
//    // 2) 첨부파일 추가
//    if (board.getAttachedFiles().size() > 0) {
//      boardDao.insertFiles(board);
//    }
//
//    return true;
//  }
//
  @Override
  public Faq get(int no) throws Exception {
    return faqDao.findByNo(no); // 첨부파일 데이터까지 조인하여 select를 한 번만 실행한다.
  }
//
//  @Transactional
//  @Override
//  public boolean delete(int no) throws Exception {
//    // 1) 첨부파일 삭제
//    boardDao.deleteFiles(no);
//
//    // 2) 게시글 삭제
//    return boardDao.delete(no) > 0;
//  }
//
  @Override
  public List<Faq> list() throws Exception {
    return faqDao.findAll();
  }

//  @Override
//  public AttachedFile getAttachedFile(int fileNo) throws Exception {
//    return boardDao.findFileByNo(fileNo);
//  }
//
//  @Override
//  public boolean deleteAttachedFile(int fileNo) throws Exception {
//    return boardDao.deleteFile(fileNo) > 0;
//  }

}








