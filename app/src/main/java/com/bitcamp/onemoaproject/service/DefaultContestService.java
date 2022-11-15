package com.bitcamp.onemoaproject.service;


import com.bitcamp.onemoaproject.dao.ContestDao;
import com.bitcamp.onemoaproject.vo.contest.Contest;
import com.bitcamp.onemoaproject.vo.contest.ContestAttachedFile;
import com.bitcamp.onemoaproject.vo.contest.ContestTeam;
import com.bitcamp.onemoaproject.vo.contest.ContestTeamField;
import com.bitcamp.onemoaproject.vo.contest.ContestTeamFieldMember;
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
  public List<Contest> list(String no, String ono, String sortCd) throws Exception {
    return contestDao.findAll(no, ono, sortCd);
  }
  
  // 공모전 모든 게시글 검색
  @Override
  public List<Contest> list2(String no, String ono, String sortCd, String sortEd, String sortVw, String sortRw) throws Exception {
    return contestDao.findAll2(no, ono, sortCd, sortEd, sortVw, sortRw);
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
  
  @Transactional
  @Override
  public void addTeam(ContestTeam contestTeam) throws Exception {
    // 1) 팀원모집하기 등록
    if (contestDao.insertTeam(contestTeam) == 0) {
      throw new Exception("팀원모집하기 등록 실패!");
    }
    // 2) 팀장 포트폴리오 등록
    if (contestTeam.getContestTeamPortfolios().size() > 0) {
      contestDao.insertTeamFiles(contestTeam);
    }
    // 3) 팀 모집분야 등록
    if (contestTeam.getContestTeamFields().size() > 0) {
      contestDao.insertTeamField(contestTeam);
    }
  }
  
  // 공모전 팀장 상세보기 페이지
  @Override
  public ContestTeam getTeamReader(int contestNo, int memberNo) throws Exception {
    return contestDao.findByReader(contestNo, memberNo);
  }
  
  // 팀 모집분야 조회
  @Override
  public List<ContestTeamField> getTeamField(int teamNo) throws Exception {
    return contestDao.findByTeamField(teamNo);
  }
  
  // 팀원모집분야지원자 조회
  @Override
  public List<ContestTeamField> getFieldMember(int fieldNo) throws Exception {
    return contestDao.findByTeamFieldMember(fieldNo);
  }
  
  // 팀원 지원하기 지원자 등록 + 포트폴리오 등록
  @Transactional
  @Override
  public void addFieldMember(ContestTeamFieldMember contestTeamFieldMember) throws Exception {
    // 1) 팀원지원하기 등록
    if (contestDao.insertFieldMember(contestTeamFieldMember) == 0) {
      throw new Exception("팀원지원하기 등록 실패!");
    }
    // 2) 팀원지원하기 포트폴리오 등록
    if (contestTeamFieldMember.getContestTeamFieldMemberPortfolioList().size() > 0) {
      contestDao.insertFieldMemberPortfolio(contestTeamFieldMember);
    }
  }
  
  // 공모전 팀원 상세보기(지원자보기)
  @Override
  public ContestTeamFieldMember getFieldMemberDetail(int fmNo) throws Exception{
    return contestDao.findByFieldMemberDetailView(fmNo);
  }
  
  // 공모전 팀원 채택하기
  
  @Override
  public boolean updateFieldMemberType(int fmNo) throws Exception {
      return contestDao.updateFieldMemberType(fmNo) > 0;
  }
}
