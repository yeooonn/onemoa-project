package com.bitcamp.onemoaproject.vo.product;

import jdk.jfr.DataAmount;

import java.sql.Date;

public class ProductReview {

  private int no;
//   private Member;
  private Product product;
  private String title;
  private String content;
  private Date createdDate;
  private int score;

  @Override
  public String toString() {
    return "ProductReview{" +
            "no=" + no +
            ", product=" + product +
            ", title='" + title + '\'' +
            ", content='" + content + '\'' +
            ", createdDate=" + createdDate +
            ", score=" + score +
            '}';
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }
}


//select 
//p.pno,
//p.pcno,
//p.title,
//p.price,
//p.vcnt,
//p.cdt,
//p.thumbnail,
//p.thumbnailpath,
//avg(rv.scope) average
//from
//product p
//left outer join product_review rv
//on p.pno = rv.pno
//where 
//p.pno=#{value}
