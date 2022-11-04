package com.bitcamp.onemoaproject.vo.contest;

import com.bitcamp.onemoaproject.vo.Member;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ContestTeam {
  private int tno;
  private String cont;
  private Date cdt;
  private Contest contestNo;
  private Member reader;
}
