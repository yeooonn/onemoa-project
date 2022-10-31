package com.bitcamp.onemoaproject.service;

import com.bitcamp.onemoaproject.dao.ContestDao;
import com.bitcamp.onemoaproject.vo.contest.Contest;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultContestService implements ContestService{
  
  @Autowired
  ContestDao contestDao;
  
  @Override
  public List<Contest> list() throws Exception {
    return contestDao.findAll();
  }
  
  @Override
  public Contest get(int no) throws Exception {
    return contestDao.findByNo(no);
  }
}
