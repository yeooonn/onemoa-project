package com.bitcamp.onemoaproject.vo.contest;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ContestTeamField {
  private int tfno;
  private int tno;
  private String name;
  private String size;
  private  boolean type;
  
  public ContestTeamField(String name, String size) {
    this.name = name;
    this.size = size;
  }
}
