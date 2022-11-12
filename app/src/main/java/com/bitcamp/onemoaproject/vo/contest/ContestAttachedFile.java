package com.bitcamp.onemoaproject.vo.contest;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ContestAttachedFile {
  private int ctstFno; // 공모전 첨부파일번호
  private String fName; // 공모전 파일이름
  private String filepath; // 공모전 파일경로
  private int ctstNo; // 공모전 번호
  
  public ContestAttachedFile() {}
  
  public ContestAttachedFile(String filepath) {
    this.filepath = filepath;
  }
  
  public ContestAttachedFile(String fName, String filepath) {
    this.fName = fName;
    this.filepath = filepath;
  }
}
