package com.bitcamp.onemoaproject.vo.contest;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ContestAttachedFile {
  private int ctstFno;
  private String fName;
  private String filepath;
  private int ctstNo;
  
  public ContestAttachedFile() {}
  
  public ContestAttachedFile(String filepath) {
    this.filepath = filepath;
  }
  
  public ContestAttachedFile(String fName, String filepath) {
    this.fName = fName;
    this.filepath = filepath;
  }
}
