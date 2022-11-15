package com.bitcamp.onemoaproject.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.bitcamp.onemoaproject.vo.portfolio.Portfolio;
import com.bitcamp.onemoaproject.vo.portfolio.PortfolioAttachedFile;

@Mapper
public interface PortfolioDao {

  int insert(Portfolio portfolio);

  Portfolio findByNo(int ptNo);
  
  int update(Portfolio portfolio);

  int delete(int no);

  //  int deleteByMember(int memberNo);

  List<Portfolio> findAll(int mno);
  
  List<Portfolio> findAll2(int mno);
  
  int insertFiles(Portfolio portfolio);

  PortfolioAttachedFile findFileByNo(int ptfNo);

  List<PortfolioAttachedFile> findFilesByPortfolio(int portfolioNo);

  int deleteFile(int ptfNo);

  int deleteFiles(int no);

  int deleteFilesByMemberPortfolios(int memberNo);
  
  // 포트폴리오 번호로 포트폴리오 조회
  List<Portfolio> findByPno(int ptNo);
}














