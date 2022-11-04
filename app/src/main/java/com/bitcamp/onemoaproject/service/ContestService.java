package com.bitcamp.onemoaproject.service;

import com.bitcamp.onemoaproject.vo.contest.Contest;
import com.bitcamp.onemoaproject.vo.contest.ContestAttachedFile;
import com.bitcamp.onemoaproject.vo.contest.ContestTeam;
import java.util.List;

public interface ContestService {
  List<Contest> list() throws Exception; // 공모전 리스트
  
  List<Contest> listTeam(boolean no) throws Exception; // 공모전 개인전, 팀전 리스트
  
  Contest get(int ctstNo) throws Exception; // 공모전 상세정보
  
  List<ContestTeam> getTeamList(int contestNumber); // 공모전 팀원구해요
  
  void add(Contest contest) throws Exception; // 공모전 등록
  
  boolean update(Contest contest) throws Exception; // 공모전 수정
  
  boolean delete(int ctstno) throws Exception; // 공모전 삭제
  
  ContestAttachedFile getContestAttachedFile(int fileNo) throws Exception;
  
  boolean contestDeleteAttachedFile(int fileNo) throws Exception;
}
