package com.bitcamp.onemoaproject.vo.contest;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ContestTeamFieldMemberPortfolio {
  private int tmpno; // 포트폴리오 번호
  private int tfmno; // 팀원모집분야 번호
  private String fpath; // 지원자포트폴리오 주소
  
  public ContestTeamFieldMemberPortfolio(String fpath) {
    this.fpath = fpath;
  }
}
