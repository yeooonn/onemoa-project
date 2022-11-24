package com.bitcamp.onemoaproject.service;

import com.bitcamp.onemoaproject.dao.QnaDao;
import com.bitcamp.onemoaproject.vo.qna.QnaAttachedFile;
import com.bitcamp.onemoaproject.vo.qna.Qna;
import com.bitcamp.onemoaproject.vo.paging.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class DefaultQnaService implements QnaService {
  
  @Autowired
  QnaDao qnaDao;
  
  @Transactional
  @Override
  public void add(Qna qna) throws Exception {
    
    // 1) 게시글 등록
    if (qnaDao.insert(qna) == 0) {
      throw new Exception("게시글 등록 실패!");
    }
    
    // 2) 첨부파일 등록
    if (qna.getQnaAttachedFiles().size() > 0) {
      qnaDao.insertFiles(qna);
    }
  }
  
  @Transactional
  @Override
  public boolean update(Qna qna) throws Exception {
    // 1) 게시글 변경
    if (qnaDao.update(qna) == 0) {
      return false;
    }
    
    // 2) 첨부파일 추가
    if (qna.getQnaAttachedFiles().size() > 0) {
      qnaDao.insertFiles(qna);
    }
    return true;
  }
  
  @Override
  public boolean answerUpdate(Qna qna) throws Exception {
    qnaDao.answerUpdate(qna);
    return true;
  }
  
  @Override
  public Qna get(int no) throws Exception {
    return qnaDao.findByNo(no);
  }
  
  @Transactional
  @Override
  public boolean delete(int no) throws Exception {
    // 1) 첨부파일 삭제
    qnaDao.deleteFiles(no);
    
    // 2) 게시글 삭제
    return qnaDao.delete(no) > 0;
  }
  
  @Override
  public List<Qna> list() throws Exception {
    return qnaDao.findAll();
  }
  
  // 전체 페이징
  @Override
  public List<Map<String, Object>> list(Map map) throws Exception {
    return qnaDao.findAllList(map);
  }
  
  @Override
  public int listCount() throws ExecutionException {
    return qnaDao.findAllCount();
  }
  
  @Override
  public QnaAttachedFile getAttachedFile(int fileNo) throws Exception {
    return qnaDao.findFileByNo(fileNo);
  }
  
  @Override
  public boolean deleteAttachedFile(int fileNo) throws Exception {
    return qnaDao.deleteFile(fileNo) > 0;
  }
  
  // 사용자 QnA 페이징
  @Override
  public List<Map<String, Object>> list2(Map map) throws Exception {
    return qnaDao.findAllList2(map);
  }
  
  @Override
  public int listCount2(int no) throws ExecutionException {
    return qnaDao.findAllCount2(no);
  }
}