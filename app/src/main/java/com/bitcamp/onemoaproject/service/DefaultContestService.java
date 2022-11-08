package com.bitcamp.onemoaproject.service;


import com.bitcamp.onemoaproject.dao.ContestDao;
import com.bitcamp.onemoaproject.vo.contest.Contest;
import com.bitcamp.onemoaproject.vo.contest.ContestAttachedFile;
import com.bitcamp.onemoaproject.vo.contest.ContestTeam;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultContestService implements ContestService{
  
  @Autowired
  ContestDao contestDao;
  
  // 공모전 모든 게시글 검색
  @Override
  public List<Contest> list(String no, String ono) throws Exception {
    System.out.println("no = " + no);
    System.out.println("ono = " + ono);
    System.out.println("contestDao.findAll(no, ono) = " + contestDao.findAll(no, ono));
    return contestDao.findAll(no, ono);
  }

  // 공모전 게시글 상세보기
  @Override
  public Contest get(int ctstNo) throws Exception {
    return contestDao.findByNo(ctstNo);
  }
  
  // 공모전 팀원구해요 목록 검색
  @Override
  public List<ContestTeam> getTeamList(int contestNumber) {
    return contestDao.findByTeamNo(contestNumber);
  }
  
  // 공모전 게시글 등록
  @Transactional
  @Override
  public void add(Contest contest) throws Exception {
    // 1) 게시글 등록
    if (contestDao.insert(contest) == 0) {
      throw new Exception("게시글 등록 실패!");
    }
    // 2) 첨부파일 등록
    if (contest.getContestAttachedFiles().size() > 0) {
      System.out.println("contest = " + contest);
      contestDao.insertFiles(contest);
    }
  }
  
  // 공모전 게시글 수정
  @Transactional
  @Override
  public boolean update(Contest contest) throws Exception {
    //1) 게시글 변경
    if (contestDao.update(contest) == 0) {
      return false;
    }
    //2) 썸네일 파일 변경
    if (contest.getThumbNail() != null) {
      contestDao.updateThumbnailFile(contest);
    }
    //3) 첨부파일 추가
    if (contest.getContestAttachedFiles().size() > 0) {
      contestDao.insertFiles(contest);
    }
    return true;
  }
  
  
  // 공모전 게시글 삭제
  @Transactional
  @Override
  public boolean delete(int ctstno) throws Exception {
    // 1) 첨부파일 삭제
    contestDao.deleteFiles(ctstno);
  
    // 2) 게시글 삭제
    return contestDao.delete(ctstno) > 0;
  }
  
  // 공모전 게시글의 첨부파일 삭제하기 위해 첨부파일 번호 검색
  @Override
  public ContestAttachedFile getContestAttachedFile(int fileNo) throws Exception {
    return contestDao.findFileByNo(fileNo);
  }
  
  // 공모전 게시글의 첨부파일 삭제
  @Override
  public boolean contestDeleteAttachedFile(int fileNo) throws Exception {
    return contestDao.deleteFile(fileNo) > 0;
  }
}
