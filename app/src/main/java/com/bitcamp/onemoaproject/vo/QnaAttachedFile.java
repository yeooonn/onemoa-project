package com.bitcamp.onemoaproject.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QnaAttachedFile {
  private int no;
  private String filename;
  private String filepath;
  private int qnaNo;


  public QnaAttachedFile() {
  }

  public QnaAttachedFile(String filename, String filepath) { this.filename = filename; this.filepath = filepath;}

  public QnaAttachedFile(String filepath) {
    this.filepath = filepath;
  }
}
