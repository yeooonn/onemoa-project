package com.bitcamp.onemoaproject.vo.contest;

public class ContestCategory {
  private int no;
  private String categoryName;
  
  @Override
  public String toString() {
    return "ContestCategory{" +
        "no=" + no +
        ", name='" + categoryName + '\'' +
        '}';
  }
  
  public int getNo() {
    return no;
  }
  
  public void setNo(int no) {
    this.no = no;
  }
  
  public String getCategoryName() {
    return categoryName;
  }
  
  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }
}
