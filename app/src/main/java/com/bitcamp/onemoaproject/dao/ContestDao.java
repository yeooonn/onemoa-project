package com.bitcamp.onemoaproject.dao;

import com.bitcamp.onemoaproject.vo.contest.Contest;
import com.bitcamp.onemoaproject.vo.contest.ContestAttachedFile;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ContestDao {
  
  List<Contest> findAll();  // 공모전 리스트
  
  List<Contest> findByTeam(boolean no); // 공모전 개인전, 팀전 리스트
  
  Contest findByNo(int ctstNo); // 공모전 상세정보
  
  int insert(Contest contest); // 공모전 등록
  
  int insertFiles(Contest contest); // 공모전 첨부파일 등록
  
  int update(Contest contest); // 공모전 수정
  
  int updateThumbnailFile(Contest contest); // 공모전 썸네일 첨부파일 수정
  
  int delete(int ctstno); // 공모전 삭제
  
  int deleteFiles(int ctstno); // 공모전 첨부파일 삭제
  
  ContestAttachedFile findFileByNo(int fileNo);
  
  int deleteFile(int fileNo);
}
