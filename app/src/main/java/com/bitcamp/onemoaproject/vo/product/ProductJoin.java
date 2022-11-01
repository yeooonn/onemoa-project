package com.bitcamp.onemoaproject.vo.product;

import java.sql.Date;

public class ProductJoin {

  private int no;
  private String categoryNo;
  private String title;
  private String content;
  private int price;
  private int viewCount;
  private Date createdDate;
  private double average;

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getCategoryNo() {
    return categoryNo;
  }

  public void setCategoryNo(String categoryNo) {
    this.categoryNo = categoryNo;
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

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public int getViewCount() {
    return viewCount;
  }

  public void setViewCount(int viewCount) {
    this.viewCount = viewCount;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public double getAverage() {
    return average;
  }

  public void setAverage(double average) {
    this.average = average;
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
