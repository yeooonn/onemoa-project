package com.bitcamp.onemoaproject.vo.product;

import com.bitcamp.onemoaproject.vo.Member;

import java.sql.Date;
import java.util.List;

public class Product {

  private int no;
  private String title;
  private String content;
  private int price;
  private int viewCount;
  private Date createdDate;
  private String thumbnail;
  private String thumbnailPath;
  private Member writer;
  private ProductCategory productCategory;
  private List<AttachedFile> attachedFiles;
  private double scope;

  @Override
  public String toString() {
    return "Product{" +
            "no=" + no +
            ", title='" + title + '\'' +
            ", content='" + content + '\'' +
            ", price=" + price +
            ", viewCount=" + viewCount +
            ", createdDate=" + createdDate +
            ", thumbnail='" + thumbnail + '\'' +
            ", thumbnailPath='" + thumbnailPath + '\'' +
            ", writer=" + writer +
            ", productCategory=" + productCategory +
            ", attachedFiles=" + attachedFiles +
            ", scope=" + scope +
            '}';
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
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

  public List<AttachedFile> getAttachedFiles() {
    return attachedFiles;
  }

  public void setAttachedFiles(List<AttachedFile> attachedFiles) {
    this.attachedFiles = attachedFiles;
  }

  public double getScope() {
    return scope;
  }

  public void setScope(double scope) {
    this.scope = scope;
  }

  public ProductCategory getProductCategory() {
    return productCategory;
  }

  public void setProductCategory(ProductCategory productCategory) {
    this.productCategory = productCategory;
  }
}
