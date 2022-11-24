package com.bitcamp.onemoaproject.vo.contest;

import java.sql.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
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
  private String team;
  private int reward;
  private String thumbNail; // thumbNail 첨부파일을 저장할 필드
  private ContestOrg contOrg; // 공모전 기관명을 저장할 필드
  private ContestCategory contCategory; // 공모전 카테고리를 저장할 필드
  
  // 첨부파일을 저장할 필드
  private List<ContestAttachedFile> contestAttachedFiles;
  
  // 공모전팀을 저장할 필드
  private ContestTeam contestTeams;
}
