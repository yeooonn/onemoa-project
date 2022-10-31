package com.bitcamp.onemoaproject.vo.contest;

import java.sql.Date;

public class Contest {
  private int ctstNo;
  private int orgNo;
  private int ctgNo;
  private String title;
  private String subCont;
  private Date createdDate;
  private Date sDate;
  private Date eDate;
  private int vw_cnt;
  private String org;
  private String appl;
  private String cont;
  private String page;
  private int size;
  private String qual;
  private boolean team;
  private int reward;
  private ContestOrg contOrg;
  private ContestCategory contCategory;
  
  
  @Override
  public String toString() {
    return "Contest{" +
        "ctstNo=" + ctstNo +
        ", orgNo=" + orgNo +
        ", ctgNo=" + ctgNo +
        ", title='" + title + '\'' +
        ", subCont='" + subCont + '\'' +
        ", createdDate=" + createdDate +
        ", sDate=" + sDate +
        ", eDate=" + eDate +
        ", vw_cnt=" + vw_cnt +
        ", org='" + org + '\'' +
        ", appl='" + appl + '\'' +
        ", cont='" + cont + '\'' +
        ", page='" + page + '\'' +
        ", size=" + size +
        ", qual='" + qual + '\'' +
        ", team=" + team +
        ", reward=" + reward +
        ", contOrg=" + contOrg +
        ", contCategory=" + contCategory +
        '}';
  }
  
  public int getCtstNo() {
    return ctstNo;
  }
  
  public void setCtstNo(int ctstNo) {
    this.ctstNo = ctstNo;
  }
  
  public int getOrgNo() {
    return orgNo;
  }
  
  public void setOrgNo(int orgNo) {
    this.orgNo = orgNo;
  }
  
  public int getCtgNo() {
    return ctgNo;
  }
  
  public void setCtgNo(int ctgNo) {
    this.ctgNo = ctgNo;
  }
  
  public String getTitle() {
    return title;
  }
  
  public void setTitle(String title) {
    this.title = title;
  }
  
  public String getSubCont() {
    return subCont;
  }
  
  public void setSubCont(String subCont) {
    this.subCont = subCont;
  }
  
  public Date getCreatedDate() {
    return createdDate;
  }
  
  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }
  
  public Date getsDate() {
    return sDate;
  }
  
  public void setsDate(Date sDate) {
    this.sDate = sDate;
  }
  
  public Date geteDate() {
    return eDate;
  }
  
  public void seteDate(Date eDate) {
    this.eDate = eDate;
  }
  
  public int getVw_cnt() {
    return vw_cnt;
  }
  
  public void setVw_cnt(int vw_cnt) {
    this.vw_cnt = vw_cnt;
  }
  
  public String getOrg() {
    return org;
  }
  
  public void setOrg(String org) {
    this.org = org;
  }
  
  public String getAppl() {
    return appl;
  }
  
  public void setAppl(String appl) {
    this.appl = appl;
  }
  
  public String getCont() {
    return cont;
  }
  
  public void setCont(String cont) {
    this.cont = cont;
  }
  
  public String getPage() {
    return page;
  }
  
  public void setPage(String page) {
    this.page = page;
  }
  
  public int getSize() {
    return size;
  }
  
  public void setSize(int size) {
    this.size = size;
  }
  
  public String getQual() {
    return qual;
  }
  
  public void setQual(String qual) {
    this.qual = qual;
  }
  
  public boolean isTeam() {
    return team;
  }
  
  public void setTeam(boolean team) {
    this.team = team;
  }
  
  public int getReward() {
    return reward;
  }
  
  public void setReward(int reward) {
    this.reward = reward;
  }
  
  public ContestOrg getContOrg() {
    return contOrg;
  }
  
  public void setContOrg(ContestOrg contOrg) {
    this.contOrg = contOrg;
  }
  
  public ContestCategory getContCategory() {
    return contCategory;
  }
  
  public void setContCategory(ContestCategory contCategory) {
    this.contCategory = contCategory;
  }
}
