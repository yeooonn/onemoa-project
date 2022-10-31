package com.bitcamp.onemoaproject.vo.contest;

public class ContestOrg {
  private int no;
  private String orgName;
  
  @Override
  public String toString() {
    return "ContestOrg{" +
        "no=" + no +
        ", name=" + orgName +
        '}';
  }
  
  public int getNo() {
    return no;
  }
  
  public void setNo(int no) {
    this.no = no;
  }
  
  public String getOrgName() {
    return orgName;
  }
  
  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }
}
