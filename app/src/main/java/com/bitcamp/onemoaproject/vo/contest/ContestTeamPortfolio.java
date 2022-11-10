package com.bitcamp.onemoaproject.vo.contest;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ContestTeamPortfolio {
  private int tpno;
  private int tno;
  private String fpath;
  
  public ContestTeamPortfolio(String fpath) {
    this.fpath = fpath;
  }
}
