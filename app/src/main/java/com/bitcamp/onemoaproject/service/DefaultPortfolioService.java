package com.bitcamp.onemoaproject.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bitcamp.onemoaproject.dao.PortfolioDao;
import com.bitcamp.onemoaproject.vo.portfolio.Portfolio;
import com.bitcamp.onemoaproject.vo.portfolio.PortfolioAttachedFile;

@Service
public class DefaultPortfolioService implements PortfolioService {

  @Autowired 
  PortfolioDao portfolioDao;

  @Transactional
  @Override
  public void add(Portfolio portfolio) throws Exception {
    // 1) 게시글 등록
    if (portfolioDao.insert(portfolio) == 0) {
      throw new Exception("게시글 등록 실패!");
    }

    // 2) 첨부파일 등록
    if (portfolio.getAttachedFiles().size() > 0) {
      portfolioDao.insertFiles(portfolio);
    }
  }

  @Override
  public Portfolio get(int ptNo) throws Exception {
    return portfolioDao.findByNo(ptNo); // 첨부파일 데이터까지 조인하여 select를 한 번만 실행한다.
  }
  
  @Transactional
  @Override
  public boolean update(Portfolio portfolio) throws Exception {
    // 1) 게시글 변경
    if (portfolioDao.update(portfolio) == 0) {
      return false;
    }

    // 2) 첨부파일 추가
    if (portfolio.getAttachedFiles().size() > 0) {
      portfolioDao.insertFiles(portfolio);
    }

    return true;
  }

  @Transactional
  @Override
  public boolean delete(int no) throws Exception {
    // 1) 첨부파일 삭제
    portfolioDao.deleteFiles(no);

    // 2) 게시글 삭제
    return portfolioDao.delete(no) > 0;
  }

  @Override
  public List<Portfolio> list(int mno) throws Exception {
    return portfolioDao.findAll(mno);
  }
  
  @Override
  public List<Portfolio> list2(int mno) throws Exception {
    return portfolioDao.findAll2(mno);
  }

  //  @Override
  @Override
  public PortfolioAttachedFile getAttachedFile(int ptfNo) throws Exception {
    return portfolioDao.findFileByNo(ptfNo);
  }

  @Override
  public boolean deleteAttachedFile(int ptfNo) throws Exception {
    return portfolioDao.deleteFile(ptfNo) > 0;
  }
  
  @Override
  public List<Portfolio> getPortfolio(int ptNo) {
    return portfolioDao.findByPno(ptNo);
  }
}








