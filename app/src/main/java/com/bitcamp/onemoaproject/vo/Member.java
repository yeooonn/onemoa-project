package com.bitcamp.onemoaproject.vo;

import java.sql.Date;

public class Member {

  private int no;
  private String name;
  private String email;
  private String password;
  private Date createdDate;
  private String nickname;
  private String tel;
  private String pstno;
  private String baseAddress;
  private String detailAddress;
  private int jobNo;
  private String bank;
  private String acount;
  private String profil;
  private String token;
  private boolean status;

  @Override
  public String toString() {
    return "Member [no=" + no + ", name=" + name + ", email=" + email + ", password=" + password
        + ", createdDate=" + createdDate + ", nickname=" + nickname + ", tel=" + tel + ", pstno="
        + pstno + ", baseAddress=" + baseAddress + ", detailAddress=" + detailAddress + ", jobNo="
        + jobNo + ", bank=" + bank + ", acount=" + acount + ", profil=" + profil + ", token="
        + token + ", status=" + status + "]";
  }

  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public Date getCreatedDate() {
    return createdDate;
  }
  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }
  public String getNickname() {
    return nickname;
  }
  public void setNickname(String nickname) {
    this.nickname = nickname;
  }
  public String getTel() {
    return tel;
  }
  public void setTel(String tel) {
    this.tel = tel;
  }
  public String getPstno() {
    return pstno;
  }
  public void setPstno(String pstno) {
    this.pstno = pstno;
  }
  public String getBaseAddress() {
    return baseAddress;
  }
  public void setBaseAddress(String baseAddress) {
    this.baseAddress = baseAddress;
  }
  public String getDetailAddress() {
    return detailAddress;
  }
  public void setDetailAddress(String detailAddress) {
    this.detailAddress = detailAddress;
  }
  public int getJobNo() {
    return jobNo;
  }
  public void setJobNo(int jobNo) {
    this.jobNo = jobNo;
  }
  public String getBank() {
    return bank;
  }
  public void setBank(String bank) {
    this.bank = bank;
  }
  public String getAcount() {
    return acount;
  }
  public void setAcount(String acount) {
    this.acount = acount;
  }
  public String getProfil() {
    return profil;
  }
  public void setProfil(String profil) {
    this.profil = profil;
  }
  public String getToken() {
    return token;
  }
  public void setToken(String token) {
    this.token = token;
  }
  public boolean isStatus() {
    return status;
  }
  public void setStatus(boolean status) {
    this.status = status;
  }



}
