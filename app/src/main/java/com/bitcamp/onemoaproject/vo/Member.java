package com.bitcamp.onemoaproject.vo;

import java.sql.Date;

public class Member {

  private int no;
  private String email;
  private String password;
  private String nickname;
  private String name;
  private String tel;
  private Date createdDate;
  private String postNo;
  private String baseAddr;
  private String detailAddr;
  private int jobNo;
  private String bank;
  private String account;
  private String profile;
  private String token;
  private boolean status;
  private String interests;

  @Override
  public String toString() {
    return "Member{" + "no=" + no + ", email='" + email + '\'' + ", password='" + password + '\''
        + ", nickname='" + nickname + '\'' + ", name='" + name + '\'' + ", tel='" + tel + '\''
        + ", createdDate=" + createdDate + ", postNo='" + postNo + '\'' + ", baseAddr='" + baseAddr
        + '\'' + ", detailAddr='" + detailAddr + '\'' + ", jobNo=" + jobNo + ", bank='" + bank
        + '\'' + ", account='" + account + '\'' + ", profile='" + profile + '\'' + ", token='"
        + token + '\'' + ", status=" + status + ", interests='" + interests + '\'' + '}';
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
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

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public String getPostNo() {
    return postNo;
  }

  public void setPostNo(String postNo) {
    this.postNo = postNo;
  }

  public String getBaseAddr() {
    return baseAddr;
  }

  public void setBaseAddr(String baseAddr) {
    this.baseAddr = baseAddr;
  }

  public String getDetailAddr() {
    return detailAddr;
  }

  public void setDetailAddr(String detailAddr) {
    this.detailAddr = detailAddr;
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

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public String getProfile() {
    return profile;
  }

  public void setProfile(String profile) {
    this.profile = profile;
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

  public String getInterests() {
    return interests;
  }

  public void setInterests(String interests) {
    this.interests = interests;
  }
}
