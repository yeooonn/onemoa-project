package com.bitcamp.onemoaproject.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.bitcamp.onemoaproject.vo.AdminMember;

@Mapper
public interface AdminMemberDao {
  AdminMember findByEmailPassword(@Param("email") String email, @Param("password") String password);

  List<AdminMember> findAll();
}
