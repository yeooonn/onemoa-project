package com.bitcamp.onemoaproject.vo.contest;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ContestTeamPortfolio {
  private int tpno; // 포트폴리오 번호
  private int tno; // 팀원 모집번호
  private String fpath; // 팀장 포트폴리오 경로
  
  public ContestTeamPortfolio(String fpath) {
    this.fpath = fpath;
  }
}
