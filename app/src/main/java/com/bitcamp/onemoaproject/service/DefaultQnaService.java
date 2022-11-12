package com.bitcamp.onemoaproject.service;

import com.bitcamp.onemoaproject.dao.QnaDao;
import com.bitcamp.onemoaproject.vo.AttachedFile;
import com.bitcamp.onemoaproject.vo.Qna;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    if (qna.getAttachedFiles().size() > 0) {
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
    if (qna.getAttachedFiles().size() > 0) {
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
    System.out.println("qna/list 호출됨!");
    return qnaDao.findAll();
  }

  @Override
  public AttachedFile getAttachedFile(int fileNo) throws Exception {
    return qnaDao.findFileByNo(fileNo);
  }

  @Override
  public boolean deleteAttachedFile(int fileNo) throws Exception {
    return qnaDao.deleteFile(fileNo) > 0;
  }

}








