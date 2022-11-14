package com.bitcamp.onemoaproject.vo.contest;

import com.bitcamp.onemoaproject.vo.Member;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class ContestTeamFieldMember {
  private int tfmno; // 팀원모집분야번호
  private int tfno; // 모집분야번호
  private int mno; // 회원번호
  private String cont; // 자기소개
  private Date date; // 등록날짜
  private boolean type; // 승인,거절
  private Member applicant;
  
  // 지원자 포트폴리오를 저장할 필드
  private List<ContestTeamFieldMemberPortfolio> contestTeamFieldMemberPortfolioList;
  
}
