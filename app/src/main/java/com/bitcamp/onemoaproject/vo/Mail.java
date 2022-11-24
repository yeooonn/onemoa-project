package com.bitcamp.onemoaproject.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Mail {
  private String from;
  private String address;
  private String ccAddress;
  private String title;
  private String content;
  private String template;
  private int checkNum;
}



