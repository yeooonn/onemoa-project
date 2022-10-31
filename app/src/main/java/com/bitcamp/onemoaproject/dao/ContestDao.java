package com.bitcamp.onemoaproject.dao;

import com.bitcamp.onemoaproject.vo.contest.Contest;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ContestDao {
  List<Contest> findAll();
  
  Contest findByNo(int contestNumber);
}
