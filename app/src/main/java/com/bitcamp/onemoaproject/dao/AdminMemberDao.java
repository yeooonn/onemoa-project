package com.bitcamp.onemoaproject.dao;

import com.bitcamp.onemoaproject.vo.AdminMember;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMemberDao {
  List<AdminMember> findAll();
}
