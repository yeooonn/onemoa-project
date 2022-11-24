package com.bitcamp.onemoaproject.vo.product;

import com.bitcamp.onemoaproject.vo.Member;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class Product {
  private int no;
  private String title;
  private String content;
  private int price;
  private int viewCount;
  private Date createdDate;
  private String thumbnail;
  private Member writer;
  private ProductCategory productCategory;
  private List<AttachedFile> attachedFiles;
  private String rule;
  private String selfIntro;
  private int status;
  List<ProductScore> productScores;
}

