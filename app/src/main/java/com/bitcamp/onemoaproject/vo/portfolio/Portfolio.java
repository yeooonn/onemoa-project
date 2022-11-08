package com.bitcamp.onemoaproject.vo.portfolio;

import java.sql.Date;
import java.util.List;
import com.bitcamp.onemoaproject.vo.Member;

public class Portfolio {

  private int ptNo;
  private int mno;
  private String title;
  private String cont;
  private Date createdDate;
  private Member member;

  // 첨부파일 정보를 저장할 필드
  private List<PortfolioAttachedFile> attachedFiles;

  @Override
  public String toString() {
    return "Portfolio [ptNo=" + ptNo + ", mno=" + mno + ", title=" + title + ", cont=" + cont
        + ", createdDate=" + createdDate + ", member=" + member + ", attachedFiles=" + attachedFiles
        + "]";
  }

  public int getPtNo() {
    return ptNo;
  }

  public void setPtNo(int ptNo) {
    this.ptNo = ptNo;
  }

  public int getMno() {
    return mno;
  }

  public void setMno(int mno) {
    this.mno = mno;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getCont() {
    return cont;
  }

  public void setCont(String cont) {
    this.cont = cont;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public Member getMember() {
    return member;
  }

  public void setMember(Member member) {
    this.member = member;
  }

  public List<PortfolioAttachedFile> getAttachedFiles() {
    return attachedFiles;
  }

  public void setAttachedFiles(List<PortfolioAttachedFile> attachedFiles) {
    this.attachedFiles = attachedFiles;
  }





}
