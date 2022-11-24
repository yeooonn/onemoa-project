package com.bitcamp.onemoaproject.vo.portfolio;

public class PortfolioAttachedFile {
  private int ptfNo;
  private String fName;
  private String filepath;
  private int ptNo;

  public PortfolioAttachedFile() {}

  public PortfolioAttachedFile(String filename, String filepath) {
    this.fName = filename;
    this.filepath = filepath;
  }

  public PortfolioAttachedFile(String filepath) {
    this.filepath = filepath;
  }

  @Override
  public String toString() {
    return "PortfolioAttachedFile [ptfNo=" + ptfNo + ", fName=" + fName + ", filepath=" + filepath
        + ", ptNo=" + ptNo + "]";
  }

  public int getPtfNo() {
    return ptfNo;
  }

  public void setPtfNo(int ptfNo) {
    this.ptfNo = ptfNo;
  }

  public String getfName() {
    return fName;
  }

  public void setfName(String fName) {
    this.fName = fName;
  }

  public String getFilepath() {
    return filepath;
  }

  public void setFilepath(String filepath) {
    this.filepath = filepath;
  }

  public int getPtNo() {
    return ptNo;
  }

  public void setPtNo(int ptNo) {
    this.ptNo = ptNo;
  }




}
