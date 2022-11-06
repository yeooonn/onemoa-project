package com.bitcamp.onemoaproject.vo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Faq {

  private int no;
  private String title;
  private String content;
  private String type;
  private Member writer;
}
