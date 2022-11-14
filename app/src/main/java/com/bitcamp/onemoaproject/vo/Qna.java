package com.bitcamp.onemoaproject.vo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
public class Qna {
  private int no;
  private String title;
  private String content;
  private LocalDateTime createdDate;
  private String answer;
  private LocalDateTime answerCdt;
  private QnaCategory category;
  private Member writer;
  private List<QnaAttachedFile> qnaAttachedFiles;
}


