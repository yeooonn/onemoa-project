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
  
  @Override
  public List<Contest> list() throws Exception {
    return contestDao.findAll();
  }
  
  @Override
  public List<Contest> listTeam(boolean no) throws Exception {
    return contestDao.findByTeam(no);
  }
  
  @Override
  public Contest get(int ctstNo) throws Exception {
    return contestDao.findByNo(ctstNo);
  }
  
  @Override
  public List<ContestTeam> getTeamList(int contestNumber) {
    return contestDao.findByTeamNo(contestNumber);
  }
  
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
  
  @Transactional
  @Override
  public boolean delete(int ctstno) throws Exception {
    // 1) 첨부파일 삭제
    contestDao.deleteFiles(ctstno);
  
    // 2) 게시글 삭제
    return contestDao.delete(ctstno) > 0;
  }
  
  @Override
  public ContestAttachedFile getContestAttachedFile(int fileNo) throws Exception {
    return contestDao.findFileByNo(fileNo);
  }
  
  @Override
  public boolean contestDeleteAttachedFile(int fileNo) throws Exception {
    return contestDao.deleteFile(fileNo) > 0;
  }
}
