package com.bitcamp.onemoaproject.service;

import com.bitcamp.onemoaproject.vo.contest.Contest;
import com.bitcamp.onemoaproject.vo.contest.ContestAttachedFile;
import com.bitcamp.onemoaproject.vo.contest.ContestTeam;
import java.util.List;

public interface ContestService {
  List<Contest> list(String no, String ono, String sortCd) throws Exception; // 공모전 리스트
  
  List<Contest> list2(String no, String ono, String sortCd, String sortEd, String sortVw, String sortRw) throws Exception; // 공모전 리스트

  Contest get(int ctstNo) throws Exception; // 공모전 상세정보
  
  List<ContestTeam> getTeamList(int contestNumber); // 공모전 팀원구해요
  
  void add(Contest contest) throws Exception; // 공모전 등록
  
  boolean update(Contest contest) throws Exception; // 공모전 수정
  
  boolean delete(int ctstno) throws Exception; // 공모전 삭제
  
  ContestAttachedFile getContestAttachedFile(int fileNo) throws Exception; // 첨부파일번호로 파일 검색
  
  boolean contestDeleteAttachedFile(int fileNo) throws Exception; // 첨부파일 번호로 첨부파일 삭제
  
  void addTeam(ContestTeam contestTeam) throws Exception;
}
