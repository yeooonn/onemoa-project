package com.bitcamp.onemoaproject.vo.contest;

import com.bitcamp.onemoaproject.vo.Member;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ContestTeam {
  private int tno;
  private int ctstno;
  private int mno;
  private String cont;
  private Date cdt;
  private Member reader;
  
  private List<ContestTeamPortfolio> contestTeamPortfolios;
  private List<ContestTeamField> contestTeamFields;
}
