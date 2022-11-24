package com.bitcamp.onemoaproject.vo;

import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Message {
  private int msgNo;
  private int sender;
  private int receiver;
  private String cont;
  private Boolean type;
  private Date cdt;
  private Member writer;
  
  List<MessageAttachedFile> messageAttachedFiles;
}
