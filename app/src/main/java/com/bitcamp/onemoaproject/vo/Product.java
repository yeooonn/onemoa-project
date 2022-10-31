package com.bitcamp.onemoaproject.vo;

import java.sql.Date;

public class Product {

  private int no;
  private String categoryNo;
  private String title;
  private String content;
  private int price;
  private int viewCount;
  private Date createdDate;
  private String thumbnail;
  private String thumbnailPath;
  private Member writer; 

  // 첨부파일 정보를 저장할 필드 
  // private List<AttachedFile> attachedFiles;

  @Override
  public String toString() {
    return "Product [no=" + no + ", categoryNo=" + categoryNo + ", title=" + title + ", content="
        + content + ", price=" + price + ", viewCount=" + viewCount + ", createdDate=" + createdDate
        + ", thumbnail=" + thumbnail + ", thumbnailPath=" + thumbnailPath + ", writer=" + writer
        + "]";
  }

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

  public String getThumbnail() {
    return thumbnail;
  }

  public void setThumbnail(String thumbnail) {
    this.thumbnail = thumbnail;
  }

  public String getThumbnailPath() {
    return thumbnailPath;
  }

  public void setThumbnailPath(String thumbnailPath) {
    this.thumbnailPath = thumbnailPath;
  }

  public Member getWriter() {
    return writer;
  }

  public void setWriter(Member writer) {
    this.writer = writer;
  }

  //  public List<AttachedFile> getAttachedFiles() {
  //    return attachedFiles;
  //  }
  //
  //  public void setAttachedFiles(List<AttachedFile> attachedFiles) {
  //    this.attachedFiles = attachedFiles;
  //  }

}
