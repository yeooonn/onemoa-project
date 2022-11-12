package com.bitcamp.onemoaproject.vo;

import com.bitcamp.onemoaproject.vo.portfolio.Portfolio;
import java.sql.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
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
  
  private List<Portfolio> portfoliosList;
}
