package com.bitcamp.onemoaproject.vo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class Qna {
  private int no;
  private String title;
  private String content;
  private Date createdDate;
  private String answer;
  private Date answerCdt;
  private QnaCategory category;
  private Member writer;
  private List<AttachedFile> attachedFiles;
}


