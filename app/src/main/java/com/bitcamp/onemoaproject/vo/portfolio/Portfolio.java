package com.bitcamp.onemoaproject.vo.portfolio;

import java.sql.Date;
import java.util.List;
import com.bitcamp.onemoaproject.vo.Member;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Portfolio {
  
  private int ptNo;
  private int mno;
  private String title;
  private String cont;
  private Date createdDate;
  private Member member;

  // 첨부파일 정보를 저장할 필드
  private List<PortfolioAttachedFile> attachedFiles;
}
