package com.bitcamp.onemoaproject.service;

import com.bitcamp.onemoaproject.vo.contest.Contest;
import java.util.List;

public interface ContestService {
  List<Contest> list() throws Exception;
  
  Contest get(int no) throws Exception;
}
