package com.bitcamp.onemoaproject.vo.contest;

import com.bitcamp.onemoaproject.vo.Member;
import java.sql.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ContestTeam {
  private int tno; // 팀원모집번호
  private int ctstno; // 공모전번호
  private int mno; // 팀장 회원번호
  private String cont; // 팀장 소개글
  private Date cdt; // 등록일
  private Member reader; // 팀장 정보
  
  // 팀장 포트폴리오를 저장할 필드
  private List<ContestTeamPortfolio> contestTeamPortfolios;
  // 모집분야를 저장할 필드
  private List<ContestTeamField> contestTeamFields;
}
