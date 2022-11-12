package com.bitcamp.onemoaproject.vo.contest;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ContestOrg {
  private int no; // 기관분류번호
  private String orgName; // 기관명
  
  public ContestOrg(int no, String orgName) {
    this.no = no;
    this.orgName = orgName;
  }
}
