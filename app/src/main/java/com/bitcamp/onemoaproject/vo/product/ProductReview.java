package com.bitcamp.onemoaproject.vo.product;

import com.bitcamp.onemoaproject.vo.Member;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.sql.Date;

@Getter
@Setter
@ToString
public class ProductReview {

  private int no;
  private Member writer;
  private Product product;
  private String title;
  private String content;
  private Date createdDate;
  private int score;
  // private double scope;
}
