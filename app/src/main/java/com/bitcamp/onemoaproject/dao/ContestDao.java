package com.bitcamp.onemoaproject.dao;

import com.bitcamp.onemoaproject.vo.contest.Contest;
import com.bitcamp.onemoaproject.vo.contest.ContestAttachedFile;
import com.bitcamp.onemoaproject.vo.contest.ContestTeam;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ContestDao {
  
  List<Contest> findAll(String no, String ono);  // 공모전 리스트
  
  Contest findByNo(int ctstNo); // 공모전 상세정보
  
  int insert(Contest contest); // 공모전 등록
  
  int insertFiles(Contest contest); // 공모전 첨부파일 등록
  
  int update(Contest contest); // 공모전 수정
  
  int updateThumbnailFile(Contest contest); // 공모전 썸네일 첨부파일 수정
  
  int delete(int ctstno); // 공모전 삭제
  
  int deleteFiles(int ctstno); // 공모전 첨부파일 삭제
  
  ContestAttachedFile findFileByNo(int fileNo); // 공모전 게시글의 첨부파일 삭제하기 위해 첨부파일 번호 검색
  
  int deleteFile(int fileNo); // 공모전 게시글의 첨부파일 삭제
  
  List<ContestTeam> findByTeamNo(int contestNumber); // 공모전 팀원구해요 목록 검색
}
