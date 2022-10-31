package com.bitcamp.onemoaproject.vo;

public class Mail {
  private String from;
  private String address;
  private String ccAddress;
  private String title;
  private String content;
  private String template;
  private int checkNum;
  
  @Override
  public String toString() {
    return "MailVo{" +
        "from='" + from + '\'' +
        ", address='" + address + '\'' +
        ", ccAddress='" + ccAddress + '\'' +
        ", title='" + title + '\'' +
        ", content='" + content + '\'' +
        ", template='" + template + '\'' +
        ", checkNum=" + checkNum +
        '}';
  }
  
  public String getFrom() {
    return from;
  }
  
  public void setFrom(String from) {
    this.from = from;
  }
  
  public String getAddress() {
    return address;
  }
  
  public void setAddress(String address) {
    this.address = address;
  }
  
  public String getCcAddress() {
    return ccAddress;
  }
  
  public void setCcAddress(String ccAddress) {
    this.ccAddress = ccAddress;
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
  
  public String getTemplate() {
    return template;
  }
  
  public void setTemplate(String template) {
    this.template = template;
  }
  
  public int getCheckNum() {
    return checkNum;
  }
  
  public void setCheckNum(int checkNum) {
    this.checkNum = checkNum;
  }
}



