package com.bitcamp.onemoaproject.dao;

import com.bitcamp.onemoaproject.vo.contest.Contest;
import com.bitcamp.onemoaproject.vo.contest.ContestAttachedFile;
import com.bitcamp.onemoaproject.vo.contest.ContestTeam;
import com.bitcamp.onemoaproject.vo.contest.ContestTeamField;
import com.bitcamp.onemoaproject.vo.contest.ContestTeamFieldMember;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ContestDao {
  
  List<Contest> findAll(@Param("no") String no, @Param("ono")String ono, @Param("sortCd") String sortCd);  // 공모전 리스트
  
  List<Contest> findAll2(
      @Param("no") String no,
      @Param("ono")String ono,
      @Param("sortCd") String sortCd,
      @Param("sortEd") String sortEd,
      @Param("sortVw") String sortVw,
      @Param("sortRw") String sortRw);  // 공모전 리스트
  
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
  
  int insertTeam(ContestTeam contestTeam); // 공모전 팀원구해요 등록
  
  int insertTeamFiles(ContestTeam contestTeam); // 공모전 팀원구해요:포트폴리오 등록
  
  int insertTeamField(ContestTeam contestTeam); // 공모전 팀원구해요:모집분야 등록
  
  // 팀장 상세보기 정보
  ContestTeam findByReader(@Param("contestNo") int contestNo,  @Param("memberNo") int memberNo);
  
  // 팀장 상세보기의 팀분류
  List<ContestTeamField> findByTeamField(int teamNo);
  
  // 팀원모집분야 지원자 리스트
  List<ContestTeamField> findByTeamFieldMember(int fieldNo);
  
  // 팀원모집분야 등록
  int insertFieldMember(ContestTeamFieldMember contestTeamFieldMember);
  
  // 팀원 모집 포트폴리오 등록
  int insertFieldMemberPortfolio(ContestTeamFieldMember contestTeamFieldMember);
  
  // 공모전 팀원 상세보기(지원자보기)
  ContestTeamFieldMember findByFieldMemberDetailView(int fmNo);
  
  // 공모전 팀원 채택하기
  int updateFieldMemberType(@Param("fmNo") int fmNo, @Param("booleanValue") String booleanValue);
}
